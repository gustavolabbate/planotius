package org.planotius.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author ggodoy
 */
public interface Browser {
    
    public String getBrowserName();
    
    public String getBrowserLocation();
    
    public WebDriver getWebDriver();
    
    public WebDriver getRemoteWebDriver(String testServer, String port);
    
    DesiredCapabilities defineCapabilities();
    
}
