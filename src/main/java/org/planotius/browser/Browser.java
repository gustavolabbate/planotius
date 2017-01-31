package org.planotius.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author ggodoy
 */
public interface Browser {

    /**
     * Get the Browsers Name
     *
     * @return String
     */
    String getBrowserName();

    /**
     * get where the binary from browser is installed on system
     *
     * @return String
     */
    String getBrowserLocation();

    /**
     * Get the webDriver instance
     *
     * @return WebDriver
     */
    WebDriver getWebDriver();

    /**
     * Get the webDriver for remote connections
     *
     * @param testServer
     * @param port
     * @return
     */
    WebDriver getRemoteWebDriver(String testServer, String port);

    /**
     * Here is where you define your capabilities...
     *
     * @return DesiredCapabilities
     */
    DesiredCapabilities defineCapabilities();

}
