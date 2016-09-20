package org.planotius.browserFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author gustavolabbate
 */
public class HtmlUnit implements Browser {

    private static final String HTMLUNIT_BROWSER = "htmlunitdriver";
    private static final Logger LOG = Logger.getLogger(InternetExplorer.class.getName());

    @Override
    public String getBrowserName() {
        return HTMLUNIT_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        DesiredCapabilities capability = DesiredCapabilities.htmlUnitWithJs();
        capability.setBrowserName("HtmlUnitDriver");

        return new PhantomJSDriver(capability);
    }

    @Override
    public WebDriver getRemoteWebDriver(String testServer, String port) {
        try {
            RemoteWebDriver remote = new RemoteWebDriver(new URL("http://" + testServer + ":" + port + "/wd/hub"), defineCapabilities());
            return remote;
        } catch (MalformedURLException ex) {
            java.util.logging.Logger.getLogger(HtmlUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.htmlUnitWithJs();
        capability.setBrowserName("HtmlUnitDriver");
        return capability;
    }

}
