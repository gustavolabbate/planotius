package org.planotius.helper;

import java.io.*;
import java.util.Properties;
import java.util.jar.JarFile;
import org.apache.log4j.Logger;

/**
 * Class to open and read properties files.
 *
 * @author gustavolabbate
 */
public class PropertiesLoader {

    private static final Logger LOG = Logger.getLogger(PropertiesLoader.class.getName());

    private static volatile Properties props;

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

            if (!"".equals(appContext)) {
                input = new FileInputStream(appContext);
                props.load(input);

            } else {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                try (InputStream stream = loader.getResourceAsStream("config.properties")) {
                    props.load(stream);
                }

            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);

        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Constructor open default custom properties file from classpath
     *
     * @param propertiesFileName
     */
    public PropertiesLoader(String propertiesFileName) {
        props = new Properties();

        FileInputStream input = null;
        try {
            input = new FileInputStream(propertiesFileName);
            props.load(input);
        } catch (IOException e) {
            LOG.debug("Trying to get file from Classloader... ", e);
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                try (InputStream stream = loader.getResourceAsStream(propertiesFileName)) {
                    props.load(stream);
                }
            } catch (IOException e1) {
                LOG.error(e1.getMessage(), e1);
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

            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;

    }
}
