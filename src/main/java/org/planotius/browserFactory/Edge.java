package org.planotius.browserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.planotius.helper.Config;

/**
 *
 * @author gustavolabbate
 */
public class Edge implements Browser {

    private static final String EDGE_BROWSER = "edge";
    private static final String EDGE_HOME = "edge.home";
    private static final Logger LOG = Logger.getLogger(Edge.class.getName());

    @Override
    public String getBrowserName() {
        return EDGE_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        return new EdgeDriver(defineCapabilities());
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setBrowserName("Edge");
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capability.setCapability("ensureCleanSession", true);

        if (Config.getConfiguration(EDGE_HOME) != null) {
            capability.setCapability("binary", Config.getConfiguration(EDGE_HOME));
            System.setProperty("webdriver.ie.driver", Config.getConfiguration(EDGE_HOME));
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
