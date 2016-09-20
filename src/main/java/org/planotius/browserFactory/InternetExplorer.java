package org.planotius.browserFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.planotius.helper.Config;

/**
 *
 * @author gustavolabbate
 */
public class InternetExplorer implements Browser {

    private static final String IE_BROWSER = "iexplore";
    private static final String IE_HOME = "iexplore.home";
    private static final Logger LOG = Logger.getLogger(InternetExplorer.class.getName());

    @Override
    public String getBrowserName() {
        return IE_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setBrowserName("internet explorer");
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capability.setCapability("ensureCleanSession", true);

        if (Config.getConfiguration("iexplore.home") != null) {
            capability.setCapability("binary", Config.getConfiguration("iexplore.home"));
            System.setProperty("webdriver.ie.driver", Config.getConfiguration("iexplore.home"));
        }
        return new InternetExplorerDriver();
    }

}
