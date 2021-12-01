package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {


    /**
     *
     * @param fileName
     * @return a list of strings, where each string is a line in the file, if no file was found an empty list is returned
     */
    public static List<String> readFilePerLine(String fileName){

        try {
            ClassLoader classLoader = FileUtil.class.getClassLoader();
            Path path = Paths.get(classLoader.getResource(fileName).toURI());
            return  Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
