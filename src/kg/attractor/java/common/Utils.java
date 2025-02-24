package kg.attractor.java.common;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
}
