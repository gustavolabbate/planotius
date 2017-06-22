package org.planotius.browserFactory;

import java.net.MalformedURLException;
import org.planotius.browser.factory.BrowserFactory;
import org.planotius.browser.Browser;
import org.junit.AfterClass;
import org.junit.Test;
import org.planotius.helper.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author gustavolabbate
 */
public class FirefoxTest {

    private static Config config = new Config();

    @Test
    public void setFirefoxHomeLocationCustom() {
        System.setProperty("firefox.home", "C:\\myBrowserDir\\firefox.exe");
        Config configCustom = new Config();
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
        Config configCustom = new Config();
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();
    }
    
    @Test
    public void shouldLoadCustomProfilefromFile() {
        System.setProperty("firefox.profile.path", "src\\test\\resources\\FirefoxProfile\\7lmpxvfi.QAautomation");
        System.setProperty("firefox.profile", "QAautomation");
        Config configCustom = new Config();
        configCustom.setFirefoxProfile(null);
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        DesiredCapabilities desiredCapabilities = browser.defineCapabilities();
    }
    
    @Test
    public void shouldLoadCustomProfile() {
        System.setProperty("firefox.profile", "QAautomation");
        Config configCustom = new Config();
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        DesiredCapabilities desiredCapabilities = browser.defineCapabilities();
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
