package kg.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.server.Cookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lesson46Server extends AuthHandler {
    public Lesson46Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/lesson46", this::lesson46Handler);

    }

    private void lesson46Handler(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();
        String name = "times";
        String cookieStr = getCookies(exchange);
        Map<String, String> cookies = Cookie.parse(cookieStr);
        String cookieValue = cookies.getOrDefault(name, "0");
        int times = Integer.parseInt(cookieValue) + 1;
        Cookie response = new Cookie<>(name, times);
        setCookie(exchange, response);
        data.put(name, times);
        data.put("cookies", cookies);
        renderTemplate(exchange, "cookie.html", data);

    }

    protected static String getCookies(HttpExchange exchange) {
        return exchange.getRequestHeaders()
                .getOrDefault("Cookie", List.of(""))
                .get(0);
    }

    protected void setCookie(HttpExchange exchange, Cookie cookie) {
        exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
    }


}
