package org.planotius.controller.functions;

import java.io.IOException;
import org.planotius.helper.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author ggodoy
 */
public class SeleniumServer {

    protected static boolean serverStarted = false;
    protected String browser;

    private static final String FF_BROWSER = "firefox";
    private static final String CHROME_HOME = "chrome.home";

    protected String testServer;
    protected String port;
    private static final Logger log = Logger.getLogger(SeleniumServer.class.getName());

    /**
     * Start the selenium server.
     *
     * @param browser
     * @param testServer
     * @param port
     */
    public SeleniumServer(String browser, String testServer, String port) {
        if (browser == null) {
            this.browser = FF_BROWSER;
        } else {
            this.browser = browser;
        }
        this.testServer = testServer;
        this.port = port;
    }

    /**
     * Start the driver. Depending on selenium.properties, could load local or
     * remote and initialize firefox or iexplore webdrivers.
     *
     * @return
     */
    public WebDriver startServer2() {
        String FF_HOME = "firefox.home";
        System.setProperty("webdriver.gecko.driver", Config.getConfiguration(FF_HOME));
        return new FirefoxDriver();
    }

    public WebDriver startServer() {
        String FF_HOME = "firefox.home";
        DesiredCapabilities capability = null;

        FirefoxProfile profile = new FirefoxProfile();

        if (browser.equalsIgnoreCase(FF_BROWSER) || browser.equalsIgnoreCase("chrome")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName(FF_BROWSER);
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setCapability("marionette", true);
            capability.setPlatform(org.openqa.selenium.Platform.ANY);

            //change locale to en_US default
            String firefoxLocale = "en_US";

            if (System.getProperty("firefox.locale") != null) {
                firefoxLocale = System.getProperty("firefox.locale");
            }

            profile.setPreference("intl.accept_languages", firefoxLocale);
            profile.setPreference("xpinstall.signatures.required", false);

            /*  Info about firefox profile
             https://groups.google.com/forum/#!topic/selenium-users/Zd5WYVZFXU0
             */
            try {
                capability.setCapability("firefox_profile", profile.toJson());
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);;
            }
            log.info("Firefox locale is: " + firefoxLocale);

            if (Config.getConfiguration(FF_HOME) != null) {
                capability.setCapability("binary", Config.getConfiguration(FF_HOME));
                //System.setProperty("webdriver.firefox.bin", Config.getConfiguration(FF_HOME));
                System.setProperty("webdriver.gecko.driver", Config.getConfiguration(FF_HOME));
            }

        }
        if (browser.equalsIgnoreCase("googlechrome") || browser.equalsIgnoreCase("chromium")) {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("Google Chrome");
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setPlatform(org.openqa.selenium.Platform.ANY);

            if (Config.getConfiguration(CHROME_HOME) != null) {
                capability.setCapability("binary", Config.getConfiguration(CHROME_HOME));
                System.setProperty("webdriver.chrome.driver", Config.getConfiguration(CHROME_HOME));
            }
        } else if (browser.equalsIgnoreCase("iexplore")) {
            capability = DesiredCapabilities.internetExplorer();
            capability.setBrowserName("internet explorer");
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capability.setCapability("ensureCleanSession", true);

            if (Config.getConfiguration("iexplore.home") != null) {
                capability.setCapability("binary", Config.getConfiguration("iexplore.home"));
                System.setProperty("webdriver.ie.driver", Config.getConfiguration("iexplore.home"));
            }
        } else if (browser.equalsIgnoreCase("htmlunitdriver") || browser.equalsIgnoreCase("nobrowser")) {
            capability = DesiredCapabilities.htmlUnitWithJs();
            capability.setBrowserName("HtmlUnitDriver");
        }

        log.info(" browser: '" + browser + "' testServer: '" + testServer + "'");
        if (browser.equalsIgnoreCase("iexplore")) {
            return new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("googlechrome") || browser.equalsIgnoreCase("chromium")) {
            return new ChromeDriver(capability);
        } else {
            return new FirefoxDriver(profile);

        }
    }

    /**
     * Get the current browser
     *
     * @return
     */
    public String getBrowser() {
        return browser;
    }

    public String getTestServer() {
        return testServer;
    }

}
