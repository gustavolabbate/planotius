package org.planotius.functions;

import org.planotius.controller.functions.SeleniumServer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class SeleniumServerTest {

    @Test
    public void testLocal() {

        try {

            String browser = "firefox";
            String url = "http://www.lenovo.com/br/pt/";
            String testServer = "localhost";
            String port = "5555";

            SeleniumServer server = new SeleniumServer(browser, testServer, port);

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
