package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private Properties properties;
    String fileName;

    public PropertyReader(String fileName){
        this.fileName=fileName;

        properties= new Properties();
        InputStream input = getClass()
                        .getClassLoader()
                        .getResourceAsStream(fileName);


        if (input == null) {
            throw new RuntimeException(fileName + " file not found");
        }
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config" + fileName, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

