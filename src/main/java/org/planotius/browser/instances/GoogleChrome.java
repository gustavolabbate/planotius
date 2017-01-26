/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.browser.instances;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.planotius.browser.decorator.BrowserDecorator;
import org.planotius.helper.Config;

/**
 *
 * @author ggodoy
 */
public class GoogleChrome extends BrowserDecorator {

    private static final String CHROME_BROWSER = "googlechrome";
    private static final String CHROME_WIN_HOME = "target/browsers/cw32/chromedriver.exe";
    private static final String CHROME_LIN64_HOME = "target/browsers/cl64/chromedriver";
    private static final String CHROME_LIN32_HOME = "target/browsers/cl32/chromedriver";
    private static String binary = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";

    private static final Logger LOG = Logger.getLogger(GoogleChrome.class.getName());

    @Override
    public String getBrowserName() {
        return CHROME_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        return new ChromeDriver(defineOptions());

    }

    private ChromeOptions defineOptions() {
        System.setProperty("webdriver.chrome.logfile", "target/chromedriver.log");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-proxy-server");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");

        if (Config.getChromeBinaryHome() != null) {
            binary = Config.getChromeBinaryHome();
        }

        options.setBinary(binary);
        LOG.info("Chrome binary configured at " + binary);

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            LOG.info("WIN os recognized. Loading googlechrome from " + CHROME_WIN_HOME);

            //options.setBinary(CHROME_WIN_HOME);
            System.setProperty("webdriver.chrome.driver", CHROME_WIN_HOME);
        } else {
            if (System.getProperty("os.arch").toLowerCase().contains("64")) {
                LOG.info("LINUX_64 os recognized. Loading googlechrome from " + CHROME_LIN64_HOME);
                //options.setBinary(CHROME_LIN64_HOME);
                System.setProperty("webdriver.chrome.driver", CHROME_LIN64_HOME);
            } else {
                LOG.info("LINUX_32 os recognized. Loading googlechrome from " + CHROME_LIN32_HOME);
                //options.setBinary(CHROME_LIN32_HOME);
                System.setProperty("webdriver.chrome.driver", CHROME_LIN32_HOME);
            }

        }

//        if (Config.getConfiguration(CHROME_HOME) != null) {
//            options.setBinary(Config.getConfiguration(CHROME_HOME));
//            System.setProperty("webdriver.chrome.driver", Config.getConfiguration(CHROME_HOME));
//            LOG.info("configuring webdriver to use: " + Config.getConfiguration(CHROME_HOME));
//        } else {
//            //options.setBinary("target/browsers/cw32/chromedriver.exe");
//            //options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
//
//            System.setProperty("webdriver.chrome.driver", "target/browsers/cw32/chromedriver.exe");
//            LOG.info("Parameter 'googlechrome.home' was not set. Using default chromedriver for windows 32 bits.");
//        }
        return options;
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("Google Chrome");
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setPlatform(org.openqa.selenium.Platform.ANY);

        LOG.info("Getting config home configuration");

//        if (Config.getConfiguration(CHROME_HOME) != null) {
//            capability.setCapability("binary", Config.getConfiguration(CHROME_HOME));
//            System.setProperty("webdriver.chrome.driver", Config.getConfiguration(CHROME_HOME));
//            LOG.info("configuring webdriver to use: " + Config.getConfiguration(CHROME_HOME));
//        } else {
//            capability.setCapability("binary", "target/browsers/cw32/chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", "target/browsers/cw32/chromedriver.exe");
//            LOG.info("Parameter 'googlechrome.home' was not set. Using default chromedriver for windows 32 bits.");
//        }
        return capability;
    }

    @Override
    public String getBrowserLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
