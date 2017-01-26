package org.planotius.pageobjects;

import org.openqa.selenium.Keys;
import org.planotius.pageobjectfactory.PageObjectFactory;
import org.planotius.controller.selenium.Element;
import org.planotius.controller.selenium.ElementDiscover;
import org.planotius.controller.selenium.Locator;

/**
 *
 * @author ggodoy
 */
public class LocalTable extends PageObjectFactory {

    //Tables
    @ElementDiscover("//table[@id='table_test']/tbody/tr[2]/td[2]")
    public Element elementFromSecondTD;

    @ElementDiscover("table_test")
    public Element tableWithHeader;

    @ElementDiscover("headless")
    public Element headlessTable;

    @ElementDiscover("myNumber1")
    public Element elementFromTDID;

    @ElementDiscover("changedByjs")
    public Element myInputText;

    @ElementDiscover("complete")
    public Element autoCompleteField;
    
    @ElementDiscover(value = "submit_button", locator = Locator.ID)
    public Element submitButton;
    
    @ElementDiscover(value = "filledByButton", locator = Locator.ID)
    public Element textFieldforButton;
    
    public String getTextfromTD() {
        return elementFromTDID.getText();
    }

    public String getTextfromSecondWithoutElementDiscoverExtFile() {
        return elementFromSecondTD.getText();
    }

    public void clickMe(){
        submitButton.click();
    }
    
    public String isValueInAutocompleteField(String stripedString, int down) {
        
        autoCompleteField.sendKeys(stripedString);

        //System.out.println("-->> " + autoCompleteField.getAttribute("value"));
        //printScreen("target/localTableAutoComplete.png");
        
        //System.out.println(down);
        for (int i = 1; i <= down; i++) {
            autoCompleteField.sendKeys(Keys.ARROW_DOWN);
          //  System.out.print("|");
        }

        autoCompleteField.sendKeys(Keys.ENTER);
        
        return autoCompleteField.getAttribute("value");
    }

    public String isValueInAutocompleteField(String stripedString) {

        autoCompleteField.sendKeys(stripedString);
        autoCompleteField.sendKeys(Keys.ARROW_DOWN);
        autoCompleteField.sendKeys(Keys.ARROW_DOWN);
        autoCompleteField.sendKeys(Keys.ENTER);
//        autoCompleteField.click();
        System.out.println("-->> " + autoCompleteField.getAttribute("value"));
        return autoCompleteField.getAttribute("value");
//        if (autoCompleteField.getAttribute("value").equals(expectedString)){
//            return true;
//        }

//        
//        System.out.println("Check1");
////        if (listSugestion.isDisplayed()) {
//        System.out.println("Check2");
//        List<WebElement> options = getDriver().findElements(By.name("browsers"));
//
//        for (WebElement option : options) {
//            System.out.println("CheckOption: " + option.getAttribute("value"));
//        }
//        System.out.println("Check3");
//        System.out.println("aComplete>> " + options.contains(expectedString));
//        System.out.println("-->> " + autoCompleteField.getAttribute("value"));
////        }
//        return false;
    }

    public String getCellValueFromTableWithHeader(Object row, Object column) {
        return tableWithHeader.getCellValueFromTable(row, column);
    }

    public String getCellValueFromHeadlessTable(Object row, Object column) {
        return headlessTable.getCellValueFromTable(row, column);
    }


}
