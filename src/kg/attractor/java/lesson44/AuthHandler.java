package kg.attractor.java.lesson44;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.common.Utils;

import kg.attractor.java.dataModels.Employee;
import kg.attractor.java.server.ContentType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.*;

public class AuthHandler extends Handler  {
    private static final String FILE_PATH = "data/jsonFiles/Employee.json";
    private static final Map<String, String> sessionStorage = new HashMap<>();

    public AuthHandler(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/profile", this::profileGet);
        registerGet("/loginFailed", this::loginFailedGet);
        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);
        registerGet("/registerFailed", this::registerFailedGet);
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
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("email", userEmail);
            renderTemplate(exchange, "/profile.ftlh", dataModel);
        } else {
            redirect303(exchange, "/login");
        }
    }

    private void loginPost(HttpExchange exchange) {
        String requestBody = getBody(exchange);
        Map<String, String> formData = Utils.parseUrlEncoded(requestBody, "&");

        String email = formData.getOrDefault("email", "").trim();
        String password = formData.getOrDefault("user-password", "").trim();

        if (email.isEmpty() || password.isEmpty()) {
            redirect303(exchange, "/loginFailed");
            return;
        }

        Optional<Map<String, String>> userOptional = findUserByEmail(email);
        if (userOptional.isPresent() && userOptional.get().get("password").equals(password)) {
            setSession(exchange, email);
            redirect303(exchange, "/profile");
        } else {
            redirect303(exchange, "/loginFailed");
        }
    }

    private Optional<Map<String, String>> findUserByEmail(String email) {
        Type userListType = new TypeToken<List<Map<String, String>>>() {}.getType();
        List<Map<String, String>> users = Utils.readFile("data/jsonFiles/Employee.json", userListType);
        return users.stream()
                .filter(user -> user.get("email").equals(email))
                .findFirst();
    }

    private String getUserEmailFromSession(HttpExchange exchange) {
        List<String> cookies = exchange.getRequestHeaders().getOrDefault("Cookie", Collections.emptyList());
        for (String cookie : cookies) {
            String[] parts = cookie.split("; ");
            for (String part : parts) {
                if (part.startsWith("session=")) {
                    String sessionId = part.substring("session=".length());
                    return sessionStorage.get(sessionId);
                }
            }
        }
        return null;
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

    private boolean isUserExists(String email) {
        Type userListType = new TypeToken<List<Map<String, String>>>() {}.getType();
        List<Map<String, String>> users = Utils.readFile("data/jsonFiles/Employee.json", userListType);
        if (users == null) {
            return false;
        }
        return users.stream()
                .anyMatch(user -> user.get("email").equals(email));
    }

    public static void saveUser(String email, String fullName, String password) {
        Type userListType = new TypeToken<List<Employee>>() {}.getType();
        List<Employee> users = Utils.readFile(FILE_PATH, userListType);
        if (users == null) {
            users = new ArrayList<>();
        }
        int newId = users.stream().mapToInt(Employee::getId).max().orElse(0) + 1;
        Employee newUser = new Employee(newId, fullName, 0, 0, email, password);
        users.add(newUser);
        Utils.writeFile(FILE_PATH, users);
        if (Handler.employees != null) {
            Handler.employees.add(newUser);
        }
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
