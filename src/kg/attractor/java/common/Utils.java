package kg.attractor.java.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> List<T> readFile(String location,Type itemsListType){
        Path path = Path.of(location);

        try{
            String json = Files.readString(path);
            return new Gson().fromJson(json,itemsListType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> parseUrlEncoded(String rawLines, String delimiter) {
        String[] pairs = rawLines.split(delimiter);
        Stream<Map.Entry<String, String>> stream = Arrays.stream(pairs).map(Utils::decode).filter(Optional::isPresent).map(Optional::get);
        return (Map)stream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    static Optional<Map.Entry<String, String>> decode(String kv) {
        if (!kv.contains("=")) {
            return Optional.empty();
        } else {
            String[] pair = kv.split("=");
            if (pair.length != 2) {
                return Optional.empty();
            } else {
                Charset utf8 = StandardCharsets.UTF_8;
                String key = URLDecoder.decode(pair[0], utf8);
                String value = URLDecoder.decode(pair[1], utf8);
                return Optional.of(Map.entry(key, value));
            }
        }
    }

    public static <T> void writeFile(String filePath, List<T> data) {
        try (FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8, false)) {
            GSON.toJson(data, writer);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static Map<String, String> parseFormData(HttpExchange exchange) {
        try {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            if (formData == null || formData.isEmpty()) {
                return Map.of();
            }

            return parseUrlEncoded(formData, "&");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при разборе данных формы", e);
        }
    }
}
