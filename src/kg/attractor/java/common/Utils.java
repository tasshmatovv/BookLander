package kg.attractor.java.common;

import com.google.gson.Gson;
import java.io.IOException;
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

}
