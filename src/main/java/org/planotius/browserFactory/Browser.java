/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.browserFactory;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author ggodoy
 */
public interface Browser {
    
    public String getBrowserName();
    
    public WebDriver getWebDriver();
    
    
    
}
