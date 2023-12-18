package org.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private final Properties properties;
    private final String propertyFilePath= "configs//config.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath(){
        String driverPath = properties.getProperty("driverPath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the config.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the config.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the config.properties file.");
    }

    public String getSeleniumHubAddress() {
        String url = properties.getProperty("seleniumHubAddress");
        if(url != null) return url;
        else throw new RuntimeException("seleniumHubAddress not specified in the config.properties file.");
    }

    public String getLocalSeleniumHubAddress() {
        String url = properties.getProperty("localSeleniumHubAddress");
        if(url != null) return url;
        else throw new RuntimeException("localSeleniumHubAddress not specified in the config.properties file.");
    }

    public String getBrowserName() {
        String url = properties.getProperty("browserName");
        if(url != null) return url;
        else throw new RuntimeException("browserName not specified in the config.properties file.");
    }

    public String getRemoteValue() {
        String url = properties.getProperty("remote");
        if(url != null) return url;
        else throw new RuntimeException("remote value is not specified in the config.properties file.");
    }

    public String getDockerValue() {
        String url = properties.getProperty("docker");
        if(url != null) return url;
        else throw new RuntimeException("docker value is not specified in the config.properties file.");
    }
}
