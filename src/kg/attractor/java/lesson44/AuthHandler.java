package kg.attractor.java.lesson44;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.common.Utils;

import kg.attractor.java.server.ContentType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.*;

public class AuthHandler extends Handler  {

    public AuthHandler(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/profile", this::profileGet);
        registerGet("/loginFailed", this::loginFailedGet);
        registerGet("/register", this::registerGet);
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("index.html");
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
            redirect303(exchange, "/profile");
        }
    }

    private void loginPost(HttpExchange exchange) {
        String requestBody = getBody(exchange);
        Map<String, String> formData = Utils.parseUrlEncoded(requestBody, "&");

        String email = formData.getOrDefault("email", "");
        String password = formData.getOrDefault("user-password", "");

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

    private void setSession(HttpExchange exchange, String userEmail) {
        String cookie = "session=" + userEmail + "; Path=/; HttpOnly";
        exchange.getResponseHeaders().add("Set-Cookie", cookie);
    }

    private String getUserEmailFromSession(HttpExchange exchange) {
        List<String> cookies = exchange.getRequestHeaders().getOrDefault("Cookie", Collections.emptyList());
        for (String cookie : cookies) {
            String[] parts = cookie.split("; ");
            for (String part : parts) {
                if (part.startsWith("session=")) {
                    return part.substring("session=".length());
                }
            }
        }
        return null;
    }

    private void registerGet(HttpExchange exchange) {
        Path path =  makeFilePath("templates/register.ftlh");
        sendFile(exchange,path,ContentType.TEXT_HTML);
    }

}
