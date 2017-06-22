package org.planotius.browser.factory;

import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.planotius.browser.Browser;
import org.junit.AfterClass;
import org.junit.Test;
import org.planotius.helper.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author gustavolabbate
 */
public class FirefoxTest extends Config {

    private static final Logger LOG = Logger.getLogger(FirefoxTest.class.getName());

    @Test
    public void setFirefoxHomeLocationCustom() {
        System.setProperty("firefox.home", "C:\\myBrowserDir\\firefox.exe");
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        LOG.info("BROWSER IS AT: " + browser.getBrowserLocation());

        assertEquals("C:\\myBrowserDir\\firefox.exe", browser.getBrowserLocation());

    }

    @Test
    public void setFirefoxHomeLocationDefault() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        LOG.info("BROWSER IS AT: " + browser.getBrowserLocation());
        assertEquals("target/browsers/gw64/geckodriver.exe", browser.getBrowserLocation());
    }

    @Test(expected = MalformedURLException.class)
    public void getRemoteWebDriver() throws MalformedURLException {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        browser.getRemoteWebDriver("bla", "blo");

    }

    @Test
    public void shouldLoadDefaultProfile() {
        System.setProperty("firefox.profile", "inexistentProfile");
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();
    }

    @Test
    public void shouldLoadCustomProfilefromFile() {
        System.setProperty("firefox.profile.path", "src\\test\\resources\\FirefoxProfile\\7lmpxvfi.QAautomation");
        System.setProperty("firefox.profile", "QAautomation");
        Config.setFirefoxProfile(null);
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();
    }

    @Test
    public void shouldLoadCustomProfile() {
        System.setProperty("firefox.profile", "QAautomation");
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();
    }

    @AfterClass
    public static void tearDown() {
        System.clearProperty("firefox.home");
    }

    @BeforeClass
    public static void setup() {
        System.clearProperty("firefox.home");
    }
}
