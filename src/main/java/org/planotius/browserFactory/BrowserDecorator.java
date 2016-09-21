package org.planotius.browserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author gustavolabbate
 */
public abstract class BrowserDecorator implements Browser {

    private static final Logger LOG = Logger.getLogger(BrowserDecorator.class.getName());

    @Override
    public WebDriver getRemoteWebDriver(String testServer, String port) {
        try {
            LOG.info("Browser is going remote on http://" + testServer + ":" + port + "/wd/hub");
            RemoteWebDriver remote = new RemoteWebDriver(new URL("http://" + testServer + ":" + port + "/wd/hub"), defineCapabilities());
            return remote;
        } catch (MalformedURLException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

}
