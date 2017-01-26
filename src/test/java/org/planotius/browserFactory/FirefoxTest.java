package org.planotius.browserFactory;

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

    @Test
    public void setFirefoxHomeLocationCustom() {
        System.setProperty("firefox.home", "C:\\myBrowserDir\\firefox.exe");

        Config config = new Config();
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        System.out.println("BROWSER IS AT: " + browser.getBrowserLocation());

        assertEquals("C:\\myBrowserDir\\firefox.exe", browser.getBrowserLocation());

    }

    @Test
    public void setFirefoxHomeLocationDefault() {
        Config config = new Config();
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        browser.defineCapabilities();

        System.out.println("BROWSER IS AT: " + browser.getBrowserLocation());
        assertEquals("target/browsers/gw64/geckodriver.exe", browser.getBrowserLocation());
    }
    
    @AfterClass
    public static void tearDown(){
        System.clearProperty("firefox.home");
    }
    
     @BeforeClass
    public static void setup(){
        System.clearProperty("firefox.home");
    }
}
