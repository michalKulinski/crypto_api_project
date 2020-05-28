package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiConfig {

    public static String getApiProp(String property) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appProps.getProperty(property);
    }


}


