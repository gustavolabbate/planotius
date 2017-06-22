package org.planotius.pageobjects;

import org.apache.log4j.Logger;
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

    private static final Logger LOG = Logger.getLogger(LocalTable.class.getName());

    //Tables
    @ElementDiscover("//table[@id='table_test']/tbody/tr[2]/td[2]")
    private Element elementFromSecondTD;

    @ElementDiscover(value = "table_test", locator = Locator.ID)
    private Element tableWithHeader;

    @ElementDiscover("headless")
    private Element headlessTable;

    @ElementDiscover("myNumber1")
    private Element elementFromTDID;

    @ElementDiscover("changedByjs")
    private Element myInputText;

    @ElementDiscover("complete")
    private Element autoCompleteField;

    @ElementDiscover(value = "submit_button", locator = Locator.ID)
    private Element submitButton;

    @ElementDiscover(value = "filledByButton", locator = Locator.ID)
    private Element textFieldforButton;
    
    private static final String ATTRIBUTE = "value";
    
    /**
     * 
     * @return 
     */
    public String getTextFieldforButton() {
        return textFieldforButton.getAttributeValue();
    }
    
    /**
     * Get a text from a TD tag
     * @return 
     */
    public String getTextfromTD() {
        return elementFromTDID.getText();
    }

    /**
     * Get text from element without a external file
     * @return 
     */
    public String getTextfromSecondWithoutElementDiscoverExtFile() {
        return elementFromSecondTD.getText();
    }

    /**
     * click on submit
     */
    public void clickMe() {
        submitButton.click();
    }

    /**
     * get value attribute from element
     * @param stripedString
     * @param down
     * @return 
     */
    public String isValueInAutocompleteField(String stripedString, int down) {

        autoCompleteField.sendKeys(stripedString);
        for (int i = 1; i <= down; i++) {
            autoCompleteField.sendKeys(Keys.ARROW_DOWN);
        }

        autoCompleteField.sendKeys(Keys.ENTER);

        return autoCompleteField.getAttribute(ATTRIBUTE);
    }

    /**
     * get value from autocomplete element
     * @param stripedString
     * @return 
     */
    public String isValueInAutocompleteField(String stripedString) {

        autoCompleteField.sendKeys(stripedString);
        autoCompleteField.sendKeys(Keys.ARROW_DOWN);
        autoCompleteField.sendKeys(Keys.ARROW_DOWN);
        autoCompleteField.sendKeys(Keys.ENTER);
        LOG.info("-->> " + autoCompleteField.getAttribute(ATTRIBUTE));
        return autoCompleteField.getAttribute(ATTRIBUTE);
    }

    /**
     * get cell value from a table with headers
     * @param row
     * @param column
     * @return 
     */
    public String getCellValueFromTableWithHeader(Object row, Object column) {
        return tableWithHeader.getCellValueFromTable(row, column);
    }
    
    /**
     * get cell value from a table without headers
     * @param row
     * @param column
     * @return 
     */
    public String getCellValueFromHeadlessTable(Object row, Object column) {
        return headlessTable.getCellValueFromTable(row, column);
    }
}
