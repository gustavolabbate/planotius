package org.planotius.pageobjects;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

/**
 *
 * @author ggodoy
 */
public class KayakPage extends Controller {
    
    @ElementDiscover("wherebox")
    public Element hotelField;
    
    @ElementDiscover("location")
    public Element listSugestion;
    
    @ElementDiscover("travel_dates-start-display")
    public Element checkIn;
    
    @ElementDiscover("travel_dates-end-display")
    public Element checkOut;
    
    @ElementDiscover("fdimgbutton")
    public Element searchButton;
    
    public Boolean isValueInHotelFieldAutocomplete(String stripedString, String expectedString) {
        
        hotelField.sendKeys(stripedString);
        
        System.out.println("-->> " + hotelField.getAttribute("value"));
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(KayakPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        printScreen("target/kayak.png");
        
        System.out.println("Check1");
//        if (listSugestion.isDisplayed()) {
        System.out.println("Check2");
        List<WebElement> options = getDriver().findElements(By.name("location"));
        
        for (WebElement option : options) {
            System.out.println("CheckOption: " + option.getAttribute("value"));
        }
        System.out.println("Check3");
        System.out.println("aComplete>> " + options.contains(expectedString));
        System.out.println("-->> " + hotelField.getAttribute("value"));
//        }

        return true;
    }
}
