package org.planotius.browser.decorator;

import org.planotius.browser.Browser;
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
    public WebDriver getRemoteWebDriver(String testServer, String port) throws MalformedURLException {
        String url = "http://" + testServer + ":" + port + "/wd/hub";
        LOG.info("Browser is going remote on " + url);
        WebDriver remote = new RemoteWebDriver(new URL(url), defineCapabilities());
        return remote;
    }

}
