package org.planotius.functions;

import org.planotius.browserFactory.Browser;
import org.planotius.browserFactory.BrowserFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.planotius.browserFactory.Firefox;
import org.planotius.browserFactory.GoogleChrome;
import org.planotius.browserFactory.HtmlUnit;
import org.planotius.browserFactory.InternetExplorer;

public class BrowserFactoryTest {

    @Test
    public void emptyBrowserPropertyShouldBeFirefox() {
        BrowserFactory browserFactory = new BrowserFactory();
        assertEquals("firefox", browserFactory.getBrowser(null).getBrowserName());
    }

    @Test
    public void browserShouldBeInternetExplorer() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("iexplore");
        assertTrue(browser instanceof InternetExplorer);
    }

    @Test
    public void browserShouldBeFirefox() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("firefox");
        assertTrue(browser instanceof Firefox);
    }

    @Test
    public void browserShouldBeGoogleChrome() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("googlechrome");
        assertTrue(browser instanceof GoogleChrome);
    }

    @Test
    public void browserShouldBeHtmlUnit() {
        BrowserFactory browserFactory = new BrowserFactory();
        Browser browser = browserFactory.getBrowser("htmlunitdriver");
        assertTrue(browser instanceof HtmlUnit);
    }

}
