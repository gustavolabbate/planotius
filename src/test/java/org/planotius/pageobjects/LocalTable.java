package org.planotius.pageobjects;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

/**
 *
 * @author ggodoy
 */
public class LocalTable extends Controller {

    //Tables
    @ElementDiscover("//table[@id='table_test']/tbody/tr[2]/td[2]")
    public Element elementFromSecondTD;

    @ElementDiscover(key = "tableHeader")
    public Element tableWithHeader;

    @ElementDiscover(key = "headlessTable")
    public Element headlessTable;

    @ElementDiscover(key = "myNumber1")
    public Element elementFromTDID;

    @ElementDiscover("changedByjs")
    public Element myInputText;

    @ElementDiscover("complete")
    public Element autoCompleteField;

    public String getTextfromTD() {
        return elementFromTDID.getText();
    }

    public String getTextfromSecondWithoutElementDiscoverExtFile() {
        return elementFromSecondTD.getText();
    }

    public String isValueInAutocompleteField(String stripedString, int down) {

        autoCompleteField.sendKeys(stripedString);

        System.out.println("-->> " + autoCompleteField.getAttribute("value"));
        printScreen("target/localTableAutoComplete.png");
        
        System.out.println(down);
        for (int i = 1; i <= down; i++) {
            autoCompleteField.sendKeys(Keys.ARROW_DOWN);
            System.out.print("|");
        }

        autoCompleteField.sendKeys(Keys.ENTER);
//        autoCompleteField.click();
        System.out.println("-->> " + autoCompleteField.getAttribute("value"));
        return autoCompleteField.getAttribute("value");
    }

    public String isValueInAutocompleteField(String stripedString) {

        autoCompleteField.sendKeys(stripedString);

        System.out.println("-->> " + autoCompleteField.getAttribute("value"));
        printScreen("target/localTableAutoComplete.png");

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
        System.out.print(".");
        return tableWithHeader.getCellValueFromTable(row, column);
    }

    public String getCellValueFromHeadlessTable(Object row, Object column) {
        System.out.print("|");
        return headlessTable.getCellValueFromTable(row, column);
    }

}
