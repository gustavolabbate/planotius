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
public class HtmlUnit extends BrowserDecorator {

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
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.htmlUnitWithJs();
        capability.setBrowserName("HtmlUnitDriver");
        return capability;
    }

}
