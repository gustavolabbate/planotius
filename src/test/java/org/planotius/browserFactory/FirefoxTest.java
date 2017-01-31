package org.planotius.browserFactory;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.planotius.browser.factory.BrowserFactory;
import org.planotius.browser.Browser;
import org.junit.AfterClass;
import org.junit.Test;
import org.planotius.helper.Config;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author gustavolabbate
 */
public class FirefoxTest {

    private static Config config = new Config();

    @Test
    public void setFirefoxHomeLocationCustom() {
        System.setProperty("firefox.home", "C:\\myBrowserDir\\firefox.exe");

        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        System.out.println("BROWSER IS AT: " + browser.getBrowserLocation());

        assertEquals("C:\\myBrowserDir\\firefox.exe", browser.getBrowserLocation());

    }

    @Test
    public void setFirefoxHomeLocationDefault() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        System.out.println("BROWSER IS AT: " + browser.getBrowserLocation());
        assertEquals("target/browsers/gw64/geckodriver.exe", browser.getBrowserLocation());
    }

    @Test
    public void getRemoteWebDriver() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();
        try {
            browser.getRemoteWebDriver("bla", "blo");
        } catch (MalformedURLException ex) {
            fail(ex.getMessage());
        }
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
