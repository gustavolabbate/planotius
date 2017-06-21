package org.planotius.browser.instances;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.planotius.browser.decorator.BrowserDecorator;

/**
 *
 * @author gustavolabbate
 */
public class PhantomJS extends BrowserDecorator {

    private static final String PHANTOM_BROWSER = "phantomJS";
    private static final String PHANTOM_WIN_HOME = "target/browsers/pw/phantomjs.exe";
    private static final String PHANTOM_LIN_HOME = "target/browsers/pl64/phantomjs";
    private static volatile String browserLocation;
    private static final Logger LOG = Logger.getLogger(PhantomJS.class.getName());

    @Override
    public String getBrowserName() {
        return PHANTOM_BROWSER;
    }

    @Override
    public WebDriver getWebDriver() {
        return new PhantomJSDriver(defineCapabilities());
    }

    @Override
    public DesiredCapabilities defineCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.phantomjs();
        capability.setJavascriptEnabled(true);
        capability.setCapability("takesScreenshot", true);

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            LOG.info("WIN os recognized. Loading phantomjs from " + PHANTOM_WIN_HOME);
            capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_WIN_HOME);
            capability.setCapability("binary", PHANTOM_WIN_HOME);
            System.setProperty("phantomjs.binary.path", PHANTOM_WIN_HOME);
            browserLocation = PHANTOM_WIN_HOME;
        } else {
            LOG.info("LINUX os recognized. Loading phantomJS from " + PHANTOM_LIN_HOME);
            capability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_LIN_HOME);
            capability.setCapability("binary", PHANTOM_LIN_HOME);
            System.setProperty("phantomjs.binary.path", PHANTOM_LIN_HOME);
            browserLocation = PHANTOM_LIN_HOME;
        }

        return capability;
    }

    @Override
    public String getBrowserLocation() {
        return browserLocation;
    }
}
