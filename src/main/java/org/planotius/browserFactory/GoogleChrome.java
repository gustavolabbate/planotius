/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.browserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.planotius.helper.Config;

/**
 *
 * @author ggodoy
 */
public class GoogleChrome implements Browser {

    private static final String CHROME_BROWSER = "googlechrome";
    private static final String CHROME_HOME = "googlechrome.home";
    private static final Logger LOG = Logger.getLogger(GoogleChrome.class.getName());

    @Override
    public String getBrowserName() {
        return CHROME_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        return new ChromeDriver(defineCapabilities());
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("Google Chrome");
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setPlatform(org.openqa.selenium.Platform.ANY);

        if (Config.getConfiguration(CHROME_HOME) != null) {
            capability.setCapability("binary", Config.getConfiguration(CHROME_HOME));
            System.setProperty("webdriver.chrome.driver", Config.getConfiguration(CHROME_HOME));
        }
        return capability;
    }

    @Override
    public WebDriver getRemoteWebDriver(String testServer, String port) {
        try {
            RemoteWebDriver remote = new RemoteWebDriver(new URL("http://" + testServer + ":" + port + "/wd/hub"), defineCapabilities());
            return remote;
        } catch (MalformedURLException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

}
