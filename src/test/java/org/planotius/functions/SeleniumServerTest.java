package org.planotius.functions;

import org.planotius.controller.functions.SeleniumServer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.planotius.helper.Config;

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
            if (server.getDriver() == null) {
                driver = server.startServer();
            }

            driver = server.getDriver();
            driver.get(url);

            try {
                System.out.println(driver.getTitle());
            } catch (Exception e) {
                fail(e.getMessage());
            }

            driver.close();
            SeleniumServer.stopServer();

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
