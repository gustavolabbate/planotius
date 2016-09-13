/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.controller.functions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ggodoy
 */
public class FindBy {

    private static final Logger LOG = Logger.getLogger(FindBy.class.getName());
    private WebDriver driver = null;

    public FindBy(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public WebElement id(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.id(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement name(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.name(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement partialLinkText(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.partialLinkText(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement xpath(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement cssSelector(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.cssSelector(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement linkText(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.linkText(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement tagName(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.tagName(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement className(String value) {
        WebElement element = null;
        try {
            element = driver.findElement(By.className(value));
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

    public WebElement imageAlt(String value) {
        WebElement element = null;
        try {
            List<WebElement> allImages = driver.findElements(By.tagName("img"));
            for (WebElement image : allImages) {
                if (image.getAttribute("alt").equals(value)) {
                    element = image;
                } else {
                    throw new NoSuchElementException("element not found");
                }
            }
        } catch (NoSuchElementException nse) {
            return null;
        }
        return element;
    }

}
