package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiConfig {

    public static final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

    public static String getApiKey() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appProps.getProperty("apiKey");
    }
}


