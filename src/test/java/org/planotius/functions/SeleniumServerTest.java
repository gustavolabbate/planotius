package org.planotius.functions;

import org.apache.log4j.Logger;
import org.planotius.controller.functions.SeleniumServer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.planotius.helper.*;


public class SeleniumServerTest {

    private static final Logger LOG = Logger.getLogger(SeleniumServerTest.class.getName());

    @BeforeClass
    public static void setup(){
        new Config();
    }
    
    @Ignore
    @Test
    public void testLocal() {

        try {

            String browser = "firefox";
            String url = "http://www.google.com.br";
            String testServer = "localhost";
            String port = "5555";

            SeleniumServer server = new SeleniumServer(browser, testServer, port);

            WebDriver driver = server.startServer();
            driver.get(url);

            try {
                LOG.info(driver.getTitle());
            } catch (Exception e) {
                fail(e.getMessage());
            }

            driver.quit();

        } catch (UnreachableBrowserException ube) {
            LOG.error("Unreachable browser exception...");
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Ignore
    @Test
    public void testRemote() {

        try {
            
            String browser = "firefox";
            String url = "http://www.google.com.br";
            String testServer = "10.35.102.136";
            String port = "4444";

            SeleniumServer server = new SeleniumServer(browser, testServer, port);

            WebDriver driver = server.startServer();
            driver.get(url);

            try {
                LOG.info(driver.getTitle());
            } catch (Exception e) {
                fail(e.getMessage());
            }

            driver.quit();

        } catch (UnreachableBrowserException ube) {
            LOG.error("Unreachable browser exception..." + ube.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
