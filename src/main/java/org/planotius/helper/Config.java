package org.planotius.helper;

/**
 *
 * @author gustavolabbate
 */
public class Config {
    private static Config config = null;
    private static PropertiesLoader configuration;
    
    public static String getConfiguration(String key) {
        if ( config == null){
            config = new Config();
            configuration = new PropertiesLoader();
        }
        return configuration.getValue(key);
    }
}
