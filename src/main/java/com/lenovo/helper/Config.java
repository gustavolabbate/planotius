package com.lenovo.helper;

import org.apache.log4j.Logger;

/**
 *
 * @author ggodoy
 */
public class Config {
    private static Config config = null;
    private static PropertiesLoader configuration;
    
    protected Config(){
        
    }
    
//    public static Config getInstance(){
//        if ( config == null){
//            config = new Config();
//            configuration = new PropertiesLoader();
//        }
//        return config;
//    }
    
    public static String getConfiguration(String key) {
        if ( config == null){
            config = new Config();
            configuration = new PropertiesLoader();
        }
        return configuration.getValue(key);
    }
}
