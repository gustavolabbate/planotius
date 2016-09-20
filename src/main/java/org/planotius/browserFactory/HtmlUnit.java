package org.planotius.browserFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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

}
