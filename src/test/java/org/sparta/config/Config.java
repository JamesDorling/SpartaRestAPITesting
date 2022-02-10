package org.sparta.config;

import org.sparta.framework.logging.LogManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
//import api_testing.config.DefaultConfigMaker;

public class Config {
    private static final Properties config;

    static {
        //Make a new properties (Initialise it)
        config = new Properties();

        if (!new File("src/test/resources/config.properties").exists()) {
            DefaultConfigMaker.createDefaultConfig();
        }
        try {
            //Load in the properties
            config.load(new BufferedReader(new FileReader("src/test/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LogManager.writeLog(Level.CONFIG, "Loaded config from file");
    }

    //Getters for the properties
    public static String getApiKey(){ return config.getProperty("api_key"); }
    public static String getUsername(){ return config.getProperty("username"); }
    public static String getPassword(){ return config.getProperty("password");}
}