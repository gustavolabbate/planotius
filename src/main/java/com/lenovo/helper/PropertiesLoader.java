package com.lenovo.helper;

import java.io.*;
import java.util.Properties;
import java.util.jar.JarFile;
import org.apache.log4j.Logger;

/**
 * Class to open and read properties files.
 *
 * @author ggodoy
 */
public class PropertiesLoader {

    private static final Logger log = Logger.getLogger(PropertiesLoader.class.getName());

    private static Properties props;

    public String getValue(String key) {
        //Try to load from system environments first
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        }
        //If no env, return from the specified file.
        return props.getProperty(key);
    }

    /**
     * Constructor open default config.properties from environment variable or
     * classpath
     */
    public PropertiesLoader() {
        props = new Properties();
        String appContext = "";
        FileInputStream input = null;

        try {
            if (System.getProperty("config.properties") != null) {
                appContext = System.getProperty("config.properties");
            }

            if (!appContext.equalsIgnoreCase("")) {
                input = new FileInputStream(appContext);
                props.load(input);

            } else {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream stream = loader.getResourceAsStream("config.properties");
                props.load(stream);
                stream.close();

            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);

        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Constructor open default custom properties file from classpath
     */
    public PropertiesLoader(String propertiesFileName) {
        props = new Properties();

        FileInputStream input = null;
        try {
            input = new FileInputStream(propertiesFileName);
            props.load(input);
        } catch (IOException e) {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream stream = loader.getResourceAsStream(propertiesFileName);
                props.load(stream);
                stream.close();
            } catch (IOException e1) {
                log.error(e.getMessage(), e);
            }

        }

    }

    /**
     * Providing a Jar File and full path name, return this properties file
     *
     * @param file
     * @param propertiesPath
     * @return
     * @throws IOException
     */
    public Properties getPropertiesFromJar(File file, String propertiesPath) throws IOException {
        try {
            JarFile jarFile = new JarFile(file);
            InputStream inputStream = jarFile.getInputStream(jarFile.getEntry(propertiesPath));

            Properties props = new Properties();
            props.load(inputStream);
            return props;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }
}
