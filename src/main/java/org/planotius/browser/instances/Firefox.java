package org.planotius.browser.instances;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.planotius.browser.decorator.BrowserDecorator;
import org.planotius.helper.Config;

/**
 *
 * @author gustavolabbate
 */
public class Firefox extends BrowserDecorator {

    private static final String FF_BROWSER = "firefox";
    private static final String FF_WIN_HOME = "target/browsers/gw64/geckodriver.exe";
    private static final String FF_LIN_HOME = "target/browsers/gl64/geckodriver";
    private static volatile String  browserLocation;
    private static final Logger LOG = Logger.getLogger(Firefox.class.getName());

    @Override
    public String getBrowserName() {
        return FF_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        return new FirefoxDriver(defineCapabilities());
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        FirefoxProfile profile = new FirefoxProfile();

        if (Config.getFirefoxProfile() != null) {
            ProfilesIni profIni = new ProfilesIni();

            if (profIni.getProfile(Config.getFirefoxProfile()) == null) {
                LOG.warn("Firefox profile: " + Config.getFirefoxProfile() + " doesn´t exist. Please provide a valid one. Continuing with default profile.");
            } else {
                profile = profIni.getProfile(Config.getFirefoxProfile());
                LOG.info("Firefox profile: " + Config.getFirefoxProfile() + " sucessfull loaded");
            }
        }

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName(FF_BROWSER);
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setCapability("marionette", true);
        System.setProperty("marionette.logging", "OFF");
        capability.setPlatform(org.openqa.selenium.Platform.ANY);

        profile.setPreference("intl.accept_languages", Config.getFirefoxLocale());
        profile.setPreference("xpinstall.signatures.required", false);

        /*  Info about firefox profile
         https://groups.google.com/forum/#!topic/selenium-users/Zd5WYVZFXU0
         */
        try {
            capability.setCapability("firefox_profile", profile.toJson());
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        if (Config.getFirefoxHome() != null) {
            capability.setCapability("binary", Config.getFirefoxHome());
            System.setProperty("webdriver.gecko.driver", Config.getFirefoxHome());
            browserLocation = Config.getFirefoxHome();
        } else {

            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                LOG.info("WIN os recognized. Loading geckodriver from " + FF_WIN_HOME);
                capability.setCapability("binary", FF_WIN_HOME);
                System.setProperty("webdriver.gecko.driver", FF_WIN_HOME);
                browserLocation = FF_WIN_HOME;
            } else if (System.getProperty("os.name").toLowerCase().contains("lin")) {
                LOG.info("LINUX os recognized. Loading geckodriver from " + FF_LIN_HOME);
                capability.setCapability("binary", FF_LIN_HOME);
                System.setProperty("webdriver.gecko.driver", FF_LIN_HOME);
                browserLocation = FF_LIN_HOME;
            }
        }
        return capability;
    }

    @Override
    public String getBrowserLocation() {
        return browserLocation;
    }
}
