package com.weborders.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;

    static {
        try {
            //location of properties file
            String path = System.getProperty("user.dir")+"/configuration.properties";
            //get that file as a stream so we can take it from there
            FileInputStream input = new FileInputStream(path);
            //FileInputStream is useful to read data from file
            //create object of Properties class
            configFile = new Properties();
            //load properties file into Properties object
            //which we can load our custom properties
            //it allows us to load our custom properties and use them in project
            configFile.load(input);
            //close the input stream at the end
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    /**
     * above static method doesn't return anything
     * try catch method since FileInputSttream is checked exception
     * we just handle it.
     * This method returns property value fromconfiguration.properties file
     * @param keyName property name
     * @return property value
     */
    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
    }
}
