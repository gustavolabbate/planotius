package org.planotius.browser.instances;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.planotius.browser.decorator.BrowserDecorator;

/**
 *
 * @author gustavolabbate
 */
public class InternetExplorer extends BrowserDecorator {

    private static final String IE_BROWSER = "iexplore";
    private static final String IE_HOME = "target/browsers/ie64/IEDriverServer.exe";
    private static final Logger LOG = Logger.getLogger(InternetExplorer.class.getName());

    @Override
    public String getBrowserName() {
        return IE_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        return new InternetExplorerDriver(defineCapabilities());
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setBrowserName("internet explorer");
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capability.setCapability("ensureCleanSession", true);

        capability.setCapability("binary", IE_HOME);
        System.setProperty("webdriver.ie.driver", IE_HOME);
        return capability;
    }

    @Override
    public String getBrowserLocation() {
        return IE_HOME;
    }

}
