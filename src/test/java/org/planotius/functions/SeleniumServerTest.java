package org.planotius.functions;

import org.planotius.controller.functions.SeleniumServer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class SeleniumServerTest {

    SeleniumServer server;
    String browser;
    String url;
    String testServer;
    String port;

    @Test
    public void emptyBrowserPropertyShouldBeFirefox() {
        server = new SeleniumServer(browser, testServer, port);
        assertEquals("firefox", server.getBrowser());
    }

    @Test
    public void testLocal() {

        try {

            browser = "firefox";
            url = "http://www.lenovo.com/br/pt/";
            testServer = "localhost";
            port = "5555";

            server = new SeleniumServer(browser, testServer, port);

            WebDriver driver = null;
            driver = server.startServer();
            driver.get(url);

            try {
                System.out.println(driver.getTitle());
            } catch (Exception e) {
                fail(e.getMessage());
            }

            driver.quit();

        } catch (UnreachableBrowserException ube) {
            System.err.println("Unreachable browser exception..." + ube.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
