package org.planotius.browserFactory;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.planotius.helper.Config;

/**
 *
 * @author gustavolabbate
 */
public class Firefox implements Browser {

    private static final String FF_BROWSER = "firefox";
    private static final String FF_HOME = "firefox.home";
    private static final Logger LOG = Logger.getLogger(Firefox.class.getName());
    
    @Override
    public String getBrowserName() {
        return FF_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        
        FirefoxProfile profile = new FirefoxProfile();
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName(FF_BROWSER);
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setCapability("marionette", true);
        capability.setPlatform(org.openqa.selenium.Platform.ANY);

        //change locale to en_US default
        String firefoxLocale = "en_US";

        if (System.getProperty("firefox.locale") != null) {
            firefoxLocale = System.getProperty("firefox.locale");
        }

        profile.setPreference("intl.accept_languages", firefoxLocale);
        profile.setPreference("xpinstall.signatures.required", false);

        /*  Info about firefox profile
         https://groups.google.com/forum/#!topic/selenium-users/Zd5WYVZFXU0
         */
        try {
            capability.setCapability("firefox_profile", profile.toJson());
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);;
        }
        LOG.info("Firefox locale is: " + firefoxLocale);

        if (Config.getConfiguration(FF_HOME) != null) {
            capability.setCapability("binary", Config.getConfiguration(FF_HOME));
            System.setProperty("webdriver.gecko.driver", Config.getConfiguration(FF_HOME));
        }

        return new FirefoxDriver(profile);
    }

}
