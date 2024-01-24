import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

public final class Utils {
    public static HashMap<String, String> getPropertiesFromFile(String filePath){
        try {
            HashMap<String,String> hashMap = new HashMap<>();
            Properties properties = new Properties();
            properties.load(new BufferedInputStream(new BufferedInputStream(new FileInputStream(filePath))));
            for(String propertyName : properties.stringPropertyNames())
                hashMap.put(propertyName, Optional.ofNullable(properties.getProperty(propertyName)).orElse(""));
            return  hashMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String directoryPath = "output.file.directory.path";
    public static String fileNameSuffix = "output.file.name.extention";
    public static String serverPort = "server.port";
    public static String delimiter = "output.file.delimiter";
}
