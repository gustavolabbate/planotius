package org.planotius.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.planotius.pageobjects.LocalTable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.planotius.controller.Controller;
import org.planotius.helper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ggodoy
 */
public class LocalTableStepsDefinitions {

    private static LocalTable localTable = new LocalTable().init();
    private static Controller controller = new Controller();
    private static final Logger LOG = LoggerFactory.getLogger(LocalTableStepsDefinitions.class.getName());

    private int rowNumber;

    
    /**
     * open browser and go to url
     */
    @Given("^I am in the local table page$")
    public void openBrowserAndUrl(){
        
        Config.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        controller.openUrl();
    }

    /**
     * 
     * @param rowNumber 
     */
    @When("^I have the row number (\\d+) in the table$")
    public void iHaveRowNumber(int rowNumber)  {
        LOG.info("I have the number " + rowNumber + " in the table.");
        this.rowNumber = rowNumber;
    }

    /**
     * Table with headers 
     */
    @When("^My table has headers$")
    public void loadHeadedTable() {
        LOG.info("Table with headers");
    }

    /**
     * Get last name
     * @param expectedLastName 
     */
    @Then("^The LastName should be (.*)$")
    public void getLastName(String expectedLastName)  {
        LOG.info("LastName is: " + localTable.getCellValueFromTableWithHeader(rowNumber, 2));
        assertThat(localTable.getCellValueFromTableWithHeader(rowNumber, 2), is(expectedLastName));
    }
    
    /**
     * Close everything
     */
    @Then("^Close everything$")
    public void quit(){
        controller.quit();
    }
}
