package org.planotius.controller;

import org.planotius.controller.functions.SeleniumServer;
import org.planotius.helper.Config;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;

/**
 *
 * @author gustavolabbate
 */
public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    public static SeleniumServer server;
    private static WebDriver driver;
    private String url;
    public static Config config;

    public Controller setUrl(String url) {
        this.url = url;
        return this;
    }

    public void quit() {
        try {
            driver.quit();
        } catch (UnreachableBrowserException ube) {
            LOG.warn("Unreachable browser exception raised (Selenium bug).");
        }
    }

    public SeleniumServer getServer() {
        return server;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public Controller() {

        LOG.debug("--------------------------------------------------");
        LOG.debug("Build-Version: 2.0.0-SNAPSHOT");
        LOG.debug("Selenium-Version: 3.0.0-beta2");
        LOG.debug("Release-date: september, 2016");
        LOG.debug("--------------------------------------------------");

        config = new Config();

        LOG.info("--------------------------------------------------");
        LOG.info("Properties loaded by Controller:");
        LOG.info("browser: " + Config.getBrowser());
        LOG.info("testServer: " + Config.getTestServer());
        LOG.info("port: " + Config.getPort());
        LOG.info("--------------------------------------------------");

        connectServer();
    }

    private void connectServer() {
        server = new SeleniumServer(Config.getBrowser(), Config.getTestServer(), Config.getPort());
        driver = server.startServer();
        LOG.info("Selenium started: [" + Config.getBrowser() + ", " + Config.getTestServer() + ", " + Config.getPort() + "]");
    }

    public String getAlertMessage() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            return alertMessage;
        } catch (Exception e) {
            return "no alert found.";
        }
    }

    public String clickAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String msg = alert.getText();
            alert.accept();
            return msg;
        } catch (Exception e) {
            LOG.info("no alert found.");
            return null;
        }
    }

    /**
     * Run a JavaScript function.
     *
     * @param function
     * @return
     */
    public Object runJavaScript(String function) {
        try {
            return ((JavascriptExecutor) getDriver()).executeScript(function);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return e;
        }

    }

    /**
     * Get the title of the page.
     *
     * @return
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Search in the current html for the desired text
     *
     * @param text
     * @return boolean
     */
    public boolean searchHtmlContents(String text) {
        boolean exist = Controller.getDriver().getPageSource().contains(text);
        LOG.info("Text : '" + text + "' found ? " + exist);
        return exist;

    }

    public boolean verifyMessage(String text) {
        return searchHtmlContents(text);
    }

    /**
     * PrintScreen and save with the desired file name.
     *
     * @param fileName
     * @return fileName
     */
    public String printScreen(String fileName) {
        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            byte[] decodedScreenshot = Base64.decodeBase64(base64Screenshot.getBytes());
            try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
                fos.write(decodedScreenshot);
            }

            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            LOG.info("Screenshot from server '" + server.getTestServer() + "' saved in: '" + fileName + "'");
            return fileName;
        } catch (WebDriverException | IOException e) {
            LOG.error(e.getMessage() + " Error when getting screenshot from server '" + server.getTestServer() + "' in: '" + fileName + "'", e);
            return null;
        }
    }

    /**
     * Open the desired url
     *
     * @return
     */
    public Controller openUrl() {
        try{
            driver.get(Config.getUrl().replace("\"", ""));
        }catch (Exception e)
        {
            LOG.error(e);
        }
        driver.manage().window().maximize();
        LOG.info("Acessing url: '" + Config.getUrl().replace("\"", "") + "'");
        return this;
    }

    public String getUrl() {
        return url;
    }
}
