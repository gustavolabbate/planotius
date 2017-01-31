package org.planotius.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.planotius.pageobjects.LocalTable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.planotius.controller.Controller;
import org.planotius.helper.*;

/**
 *
 * @author ggodoy
 */
public class LocalTableStepsDefinitions {

    private static LocalTable localTable = new LocalTable().init();
    static Controller controller = new Controller();

    private int rowNumber;

//    @BeforeClass
//    public static void setup() {
//        localTable = new LocalTable().init();
//
//        controller.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
//        controller.openUrl();
//
//    }

    @Given("^I am in the local table page$")
    public void openBrowserAndUrl(){
        
        Config.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        controller.openUrl();
    }
    
    @When("^I have the row number (\\d+) in the table$")
    public void iHaveRowNumber(int rowNumber) throws Throwable {
        System.out.println("I have the number " + rowNumber + " in the table.");
        this.rowNumber = rowNumber;
    }

    @When("^My table has headers$")
    public void loadHeadedTable() throws Throwable {
        System.out.println("Table with headers");
    }

    @Then("^The LastName should be (.*)$")
    public void getLastName(String expectedLastName) throws Throwable {
        System.out.println("LastName is: " + localTable.getCellValueFromTableWithHeader(rowNumber, 2));
        assertThat(localTable.getCellValueFromTableWithHeader(rowNumber, 2), is(expectedLastName));
    }
    
    @Then("^Close everything$")
    public void quit(){
        controller.quit();
    }
}
