package kg.attractor.java.lesson44;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.common.Utils;

import kg.attractor.java.dataModels.Employee;
import kg.attractor.java.server.ContentType;
import kg.attractor.java.server.Cookie;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.*;

public class AuthHandler extends Handler  {
    private static final String FILE_PATH = "data/jsonFiles/Employee.json";

    public AuthHandler(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/profile", this::profileGet);
        registerGet("/loginFailed", this::loginFailedGet);
        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);
        registerGet("/registerFailed", this::registerFailedGet);
        registerPost("/logout", this::logoutPost);
    }

    private void logoutPost(HttpExchange exchange) {
        exchange.getRequestHeaders().getOrDefault("Cookie", Collections.emptyList()).stream()
                .flatMap(cookie -> Arrays.stream(cookie.split("; ")))
                .filter(part -> part.startsWith("session="))
                .map(part -> part.substring("session=".length()))
                .findFirst()
                .ifPresent(sessionStorage::remove);
        Headers headers = exchange.getResponseHeaders();
        headers.add("Set-Cookie", "session=; Path=/; HttpOnly; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:00 GMT");
        redirect303(exchange, "/");
    }

    protected void setCookie(HttpExchange exchange, Cookie cookie) {
        Headers headers = exchange.getResponseHeaders();
        headers.add("Set-Cookie", cookie.toString());
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("templates/index.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void loginFailedGet(HttpExchange exchange) {
        Path path = makeFilePath("templates/loginFailed.ftlh");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void profileGet(HttpExchange exchange) {
        String userEmail = getUserEmailFromSession(exchange);
        if (userEmail != null) {
            Employee employee = employees.stream()
                    .filter(e -> e.getEmail().equals(userEmail))
                    .findFirst()
                    .orElse(null);

            if (employee != null) {
                Map<String, Object> dataModel = new HashMap<>();
                dataModel.put("fullName", employee.getFullName());
                dataModel.put("email", userEmail);
                dataModel.put("currentBooks", getBookNamesByIds(employee.getListCurrentBooks()));
                dataModel.put("pastBooks", getBookNamesByIds(employee.getListPastBooks()));

                renderTemplate(exchange, "/profile.ftlh", dataModel);
                return;
            }
        }
        redirect303(exchange, "/login");
    }

    private void loginPost(HttpExchange exchange) {
        String requestBody = getBody(exchange);
        Map<String, String> formData = Utils.parseUrlEncoded(requestBody, "&");

        String email = formData.getOrDefault("email", "").trim();
        String password = formData.getOrDefault("password", "").trim();

        if (email.isEmpty() || password.isEmpty()) {
            redirect303(exchange, "/loginFailed");
            return;
        }

        Optional<Employee> userOptional = findUserByEmail(email);
        if (userOptional.isPresent()) {
            Employee user = userOptional.get();
            if (user.getPassword().equals(password)) {
                setSession(exchange, email);
                redirect303(exchange, "/profile");
            } else {
                redirect303(exchange, "/loginFailed");
            }
        } else {
            redirect303(exchange, "/loginFailed");
        }
    }


    private Optional<Employee> findUserByEmail(String email) {
        Type userListType = new TypeToken<List<Employee>>() {}.getType();
        List<Employee> users = Utils.readFile(FILE_PATH, userListType);
        if (users == null) {
            return Optional.empty();
        }
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }


    private void registerGet(HttpExchange exchange) {
        Path path =  makeFilePath("templates/register.ftlh");
        sendFile(exchange,path,ContentType.TEXT_HTML);
    }

    private void registerPost(HttpExchange exchange) {
        String requestBody = getBody(exchange);
        Map<String, String> formData = Utils.parseUrlEncoded(requestBody, "&");

        String fullName = formData.getOrDefault("fullName", "").trim();
        String email = formData.getOrDefault("email", "").trim();
        String password = formData.getOrDefault("password", "").trim();

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            redirect303(exchange, "/registerFailed");
            return;
        }

        if (isUserExists(email)) {
            redirect303(exchange, "/registerFailed");
        } else {
            saveUser(email, fullName, password);
            redirect303(exchange, "/login");
        }
    }

    public static boolean isUserExists(String email) {
        Type userListType = new TypeToken<List<Employee>>() {}.getType();
        List<Employee> users = Utils.readFile(FILE_PATH, userListType);
        if (users == null) {
            return false;
        }
        boolean exists = users.stream().anyMatch(user -> user.getEmail().equals(email));
        return exists;
    }

    public static void saveUser(String email, String fullName, String password) {
        Type userListType = new TypeToken<List<Employee>>() {}.getType();
        List<Employee> users = Utils.readFile(FILE_PATH, userListType);
        if (users == null) {
            users = new ArrayList<>();
        }
        int newId = users.stream().mapToInt(Employee::getId).max().orElse(0) + 1;
        Employee newUser = new Employee(newId, fullName, null, null, email, password);
        users.add(newUser);
        Utils.writeFile(FILE_PATH, users);
    }

    private void registerFailedGet(HttpExchange exchange) {
        Path path = makeFilePath("templates/registerFailed.ftlh");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void setSession(HttpExchange exchange, String userEmail) {
        String sessionId = UUID.randomUUID().toString();
        sessionStorage.put(sessionId, userEmail);
        String cookie = "session=" + sessionId + "; Path=/; HttpOnly; Max-Age=600";
        exchange.getResponseHeaders().add("Set-Cookie", cookie);
    }

}
