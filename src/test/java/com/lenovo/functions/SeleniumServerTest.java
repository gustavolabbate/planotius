package com.lenovo.functions;

import com.lenovo.controller.functions.SeleniumServer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

public class SeleniumServerTest {

    SeleniumServer server;
    String browser;
    String url;
    String testServer;
    String port;

    @Test
    public void testLocal() {

        try {

            browser = "firefox";
            url = "http://www.lenovo.com/br/pt/";
            testServer = "localhost";
            port = "5555";

            server = new SeleniumServer(browser, testServer, port);
            WebDriver driver = server.getDriver();
            driver.get(url);

            try {
                System.out.println(driver.getTitle());
            } catch (Exception e) {
                fail(e.getMessage());
            }

            driver.close();
            SeleniumServer.stopServer();
            /*WebElement busca = driver.findElement(By.name("q"));
             busca.sendKeys("Thinkserver");
             busca.submit();
             assertTrue(driver.getPageSource().contains("Tower Servers"));*/

        } catch (Exception e) {
            fail(e.getMessage());
        }
        /*finally {
         SeleniumServer.stopServer();
         }*/
    }
}
