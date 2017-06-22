package org.planotius.controller;

import org.planotius.controller.selenium.SeleniumServer;
import org.planotius.helper.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.internal.BuildInfo;

/**
 *
 * @author gustavolabbate
 */
public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    private static volatile SeleniumServer server;
    private static volatile WebDriver driver;
    private static Config config = new Config();

    /**
     * Controller constructor
     */
    public Controller() {
        BuildInfo buildInfo = new BuildInfo();
        String revision = buildInfo.getBuildRevision();
        String version = buildInfo.getReleaseLabel();

        LOG.debug("--------------------------------------------------");
        LOG.debug("Build-Version: " + this.getClass().getPackage().getImplementationVersion());
        LOG.debug("Selenium-Version: " + version + " revision: " + revision);
        LOG.debug("Release-date: september, 2016");
        LOG.debug("--------------------------------------------------");

        LOG.info("--------------------------------------------------");
        LOG.info("Properties loaded by Controller:");
        LOG.info("browser: " + config.getBrowser());
        LOG.info("testServer: " + config.getTestServer());
        LOG.info("port: " + config.getPort());
        LOG.info("--------------------------------------------------");

        try {
            connectServer();
        } catch (MalformedURLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    /**
     * driver.quit
     */
    public void quit() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
            driver.quit();
        } catch (IOException | UnreachableBrowserException ube) {
            LOG.warn("Unreachable browser exception raised (Selenium bug).", ube);
        }
    }

    public SeleniumServer getServer() {
        return server;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void connectServer() throws MalformedURLException {
        server = new SeleniumServer(Config.getBrowser(), Config.getTestServer(), Config.getPort());
        driver = server.startServer();
        LOG.info("Selenium started: [" + Config.getBrowser() + ", "
                + Config.getTestServer() + ", " + Config.getPort() + "]");
    }

    /**
     * get message from an alert
     *
     * @return
     */
    public String getAlertMessage() {
        try {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        } catch (Exception e) {
            LOG.debug("No alert found", e);
            return "no alert found.";
        }
    }

    /**
     * Click on the alert
     *
     * @return
     */
    public String clickAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String msg = alert.getText();
            alert.accept();
            return msg;
        } catch (Exception e) {
            LOG.debug("No alert found", e);
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

            byte[] decodedScreenshot = Base64.decodeBase64(base64Screenshot.getBytes(StandardCharsets.UTF_8));
            try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
                fos.write(decodedScreenshot);
            }

            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            LOG.debug(f.getAbsolutePath());
            LOG.info("Screenshot from server '" + server.getTestServer() + "' saved in: '" + fileName + "'");
            return fileName;
        } catch (WebDriverException | IOException e) {
            LOG.error(e.getMessage() + " Error when getting screenshot from server '"
                    + server.getTestServer() + "' in: '" + fileName + "'", e);
            return null;
        }
    }

    /**
     * Open the desired url
     *
     * @return
     */
    public Controller openUrl() {
        try {
            driver.get(Config.getUrl().replace("\"", ""));
        } catch (Exception e) {
            LOG.error(e);
        }
        driver.manage().window().maximize();
        LOG.info("Acessing url: '" + Config.getUrl().replace("\"", "") + "'");
        return this;
    }
}
