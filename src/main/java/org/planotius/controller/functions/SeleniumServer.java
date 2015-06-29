package org.planotius.controller.functions;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.planotius.helper.Config;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author ggodoy
 */
public class SeleniumServer {

    protected static WebDriver driver = null;
    protected static boolean serverStarted = false;
    protected String browser;
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
        this.browser = browser;
        this.testServer = testServer;
        this.port = port;
        if (driver == null) {
            driver = startServer();
        }
    }

    /**
     * Start the driver. Depending on selenium.properties, could load local or
     * remote and initialize firefox or iexplore webdrivers.
     *
     * @return
     */
    public WebDriver startServer() {
        DesiredCapabilities capability = null;
        FirefoxProfile profile = new FirefoxProfile();
        if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setPlatform(org.openqa.selenium.Platform.ANY);

            //change locale to pt-br
            String firefoxLocale = "en_US";

            if (System.getProperty("firefox.locale") != null) {
                firefoxLocale = System.getProperty("firefox.locale");
            }
            log.info("Firefox locale is: " + firefoxLocale);
            profile.setPreference("intl.accept_languages", firefoxLocale);
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability("firefox_profile", profile.toString());

            if (Config.getConfiguration("firefox.home") != null) {
                capability.setCapability("binary", Config.getConfiguration("firefox.home"));
                System.setProperty("webdriver.firefox.bin", Config.getConfiguration("firefox.home"));
            }

        }
        if (browser.equalsIgnoreCase("googlechrome") || browser.equalsIgnoreCase("chromium")) {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("Google Chrome");
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capability.setPlatform(org.openqa.selenium.Platform.ANY);

            if (Config.getConfiguration("chrome.home") != null) {
                capability.setCapability("binary", Config.getConfiguration("chrome.home"));
                System.setProperty("webdriver.chrome.driver", Config.getConfiguration("chrome.home"));
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

        if (testServer.equalsIgnoreCase("localhost")) {
            if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome")) {
                driver = new FirefoxDriver(profile);
            } else if (browser.equalsIgnoreCase("iexplore")) {
                driver = new InternetExplorerDriver();
            } else if (browser.equalsIgnoreCase("googlechrome") || browser.equalsIgnoreCase("chromium")) {
                driver = new ChromeDriver(capability);
            } else if (browser.equalsIgnoreCase("htmlunitdriver") || browser.equalsIgnoreCase("nobrowser")) {
                HtmlUnitDriver driver = new HtmlUnitDriver() {
                    @Override
                    protected WebClient modifyWebClient(WebClient webClient) {
                        WebClient answer = super.modifyWebClient(webClient);
//                        WebClient answer = new WebClient(BrowserVersion.FIREFOX_24);
                        answer.addRequestHeader("Accept", "text/html");
                        answer.getCookieManager().setCookiesEnabled(true);
                        return answer;
                    }
                };
                driver.setJavascriptEnabled(true);
                log.info(" browser: '" + browser + "' testServer: '" + testServer + "'");
                return driver;
            }
        } // Running Remote
        else {

            try {
//                driver = (WebDriver) new ExtendedRemoteWebDriver(new URL("http://" + testServer + ":" + port + "/wd/hub"), capability);
                driver = new RemoteWebDriver(new URL("http://" + testServer + ":" + port + "/wd/hub"), capability);

            } catch (MalformedURLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }

        log.info(" browser: '" + browser + "' testServer: '" + testServer + "'");
        return driver;
    }

    /**
     * Stop the running webDriver instance.
     *
     * @return
     */
    public static WebDriver stopServer() {

        if (driver != null) {
//            driver.close();
            driver.quit();
            driver = null;
        }
        log.info("server stopped.");
        return driver;
    }

    /**
     * Get the current WebDriver
     *
     * @return
     */
    public static WebDriver getDriver() {
        return driver;
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
