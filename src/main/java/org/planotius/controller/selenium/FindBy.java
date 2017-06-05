package org.planotius.controller.selenium;

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
        try {
            WebElement element = driver.findElement(By.id(value));
            LOG.debug("Element [" + value + "] found using ID.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }

    }

    public WebElement name(String value) {
        try {
            WebElement element = driver.findElement(By.name(value));
            LOG.debug("Element [" + value + "] found using NAME.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }

    }

    public WebElement partialLinkText(String value) {
        try {
            WebElement element = driver.findElement(By.partialLinkText(value));
            LOG.debug("Element [" + value + "] found using PARTIALLINKTEXT.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public WebElement xpath(String value) {
        try {
            WebElement element = driver.findElement(By.xpath(value));
            LOG.debug("Element [" + value + "] found using XPATH.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public WebElement cssSelector(String value) {
        try {
            WebElement element = driver.findElement(By.cssSelector(value));
            LOG.debug("Element [" + value + "] found using CSS.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public WebElement linkText(String value) {
        try {
            WebElement element = driver.findElement(By.linkText(value));
            LOG.debug("Element [" + value + "] found using LINKTEXT.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public WebElement tagName(String value) {
        try {
            WebElement element = driver.findElement(By.tagName(value));
            LOG.debug("Element [" + value + "] found using TAGNAME.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public WebElement className(String value) {
        try {
            WebElement element = driver.findElement(By.className(value));
            LOG.debug("Element [" + value + "] found using CLASSNAME.");
            return element;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public WebElement imageAlt(String value) {
        try {
            WebElement element = null;
            List<WebElement> allImages = driver.findElements(By.tagName("img"));
            for (WebElement image : allImages) {
                if (image.getAttribute("alt").equals(value)) {
                    element = image;
                    LOG.debug("Element [" + value + "] found using ALT_of_IMAGE.");
                    return element;
                }
            }

        } catch (NoSuchElementException nse) {
            LOG.debug(nse);
        }
        return null;
    }

}
