package org.example.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DefaultConfigMaker {

    public static void createDefaultConfig() {
        Properties properties = new Properties();
        try {
            properties.setProperty("api_key", "");

            File configLocation = new File("src/test/resources");
            if(!configLocation.mkdir()) {
                System.out.println("Failed to make directory! Directory may already exist.");
            }
            properties.store(new FileWriter("src/test/resources/config.properties"), null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}