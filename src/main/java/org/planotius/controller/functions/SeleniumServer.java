package org.planotius.controller.functions;

import org.planotius.browserFactory.BrowserFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.planotius.browserFactory.Browser;

/**
 *
 * @author ggodoy
 */
public class SeleniumServer {

    protected BrowserFactory browserFactory = new BrowserFactory();
    protected String browser;

    protected String testServer;
    protected String port;
    private static final Logger log = Logger.getLogger(SeleniumServer.class.getName());

    /**
     * Start the selenium server.
     *
     * @param browser
     * @param testServer
     * @param port
     */
    public SeleniumServer(String browser, String testServer, String port) {
        this.browser = browser;
        this.testServer = testServer;
        this.port = port;
    }

    /**
     * Start the driver. Depending on selenium.properties, could load local or
     * remote and initialize firefox or iexplore webdrivers.
     *
     * @return
     */
    public WebDriver startServer() {
        log.info(" browser: '" + browser + "' testServer: '" + testServer + "'");
        if (this.testServer.equalsIgnoreCase("localhost")) {
            return browserFactory.getBrowser(browser).getWebDriver();
        } else {
            return browserFactory.getBrowser(browser).getRemoteWebDriver(testServer, port);
        }
    }

    public String getTestServer() {
        return testServer;
    }
}
