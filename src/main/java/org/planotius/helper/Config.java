package org.planotius.helper;

import org.apache.log4j.Logger;
import org.planotius.controller.Controller;

/**
 *
 * @author gustavolabbate
 */
public class Config {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());

    private static Config config = null;
    private static PropertiesLoader configuration;

    private static String testServer;
    private static String port;
    private static String browser;
    private static String url;
    private static String firefoxLocale;
    private static String firefoxProfile;
    private static String firefoxHome;

    public Config() {
        PropertiesLoader properties = new PropertiesLoader();
        Config.browser = properties.getValue("browser");

        if (System.getProperty("browser") != null) {
            Config.browser = System.getProperty("browser");
        } else if (properties.getValue("browser") != null) {
            Config.browser = properties.getValue("browser");
        } else {
            Config.browser = "firefox";
            LOG.debug("'browser' key not defined, using default '" + Config.browser + "'");
        }

        if (System.getProperty("testserver") != null) {
            Config.testServer = System.getProperty("testserver");
        } else if (properties.getValue("testserver") != null) {
            Config.testServer = properties.getValue("testserver");
        } else {
            Config.testServer = "localhost";
            LOG.debug("'testserver' key not defined, using default '" + Config.testServer + "'");
        }

        if (System.getProperty("port") != null) {
            Config.port = System.getProperty("port");
        } else if (properties.getValue("port") != null) {
            Config.port = properties.getValue("port");
        } else {
            Config.port = "5555";
            LOG.debug("'port' key not defined, using default '" + Config.port + "'");
        }

        if (Config.url == null) {
            if (System.getProperty("url") != null) {
                Config.url = System.getProperty("url");
            } else if (properties.getValue("url") != null) {
                Config.url = properties.getValue("url");
            } else {
                Config.url = "";
                LOG.debug("'url' key not defined, using empty.");
            }
        }

        if (System.getProperty("firefox.locale") != null) {
            Config.firefoxLocale = System.getProperty("firefox.locale");
        } else if (properties.getValue("firefox.locale") != null) {
            Config.firefoxLocale = properties.getValue("firefox.locale");
        } else {
            Config.firefoxLocale = "en_US";
            LOG.debug("'firefox.locale' key not defined, using default '" + Config.firefoxLocale + "'");
        }

        if (Config.firefoxProfile == null) {
            if (System.getProperty("firefox.profile") != null) {
                Config.firefoxProfile = System.getProperty("firefox.profile");
                LOG.info("firefox Profile: " + Config.firefoxProfile);
            } else if (properties.getValue("firefox.profile") != null) {
                Config.firefoxProfile = properties.getValue("firefox.profile");
                LOG.info("firefox Profile: " + Config.firefoxProfile);
            } else {
                Config.firefoxProfile = null;
                LOG.debug("'firefox.profile' key not defined, will not use any profile.");
            }
        }

        if (System.getProperty("firefox.home") != null) {
            Config.firefoxHome = System.getProperty("firefox.home");
        } else if (properties.getValue("firefox.home") != null) {
            Config.firefoxHome = properties.getValue("firefox.home");
        } else {
            Config.firefoxHome = "target/geckodriver.exe";
            LOG.debug("'firefox.home' key not defined, using default '" + Config.firefoxHome + "'");
        }

    }

    public static String getConfiguration(String key) {
        if (config == null) {
            config = new Config();
            configuration = new PropertiesLoader();
        }
        return configuration.getValue(key);
    }

    public static String getTestServer() {
        return testServer;
    }

    public static String getPort() {
        return port;
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getUrl() {
        return url;
    }

    public static String getFirefoxLocale() {
        return firefoxLocale;
    }

    public static String getFirefoxProfile() {
        return firefoxProfile;
    }

    public static String getFirefoxHome() {
        return firefoxHome;
    }

    public static void setTestServer(String testServer) {
        Config.testServer = testServer;
    }

    public static void setPort(String port) {
        Config.port = port;
    }

    public static void setBrowser(String browser) {
        Config.browser = browser;
    }

    public static void setUrl(String url) {
        Config.url = url;
    }

    public static void setFirefoxLocale(String firefoxLocale) {
        Config.firefoxLocale = firefoxLocale;
    }

    public static void setFirefoxProfile(String firefoxProfile) {
        Config.firefoxProfile = firefoxProfile;
    }

    public static void setFirefoxHome(String firefoxHome) {
        Config.firefoxHome = firefoxHome;
    }

}
