package org.planotius.functions;

import org.planotius.controller.Controller;
import org.planotius.pageobjects.LocalTable;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author ggodoy
 */
public class LocalTableTest {

    static LocalTable table;
    static Controller controller = new Controller();

    @BeforeClass
    public static void setup() {
        table = new LocalTable().init();

        controller.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        controller.openUrl();

    }

    @Test
    public void shouldVerifyTextOnPage(){
        assertTrue(controller.verifyMessage("Headless Table"));
    }
    
    @Test
    public void shouldGetValuesBasedOnStringRow() {

        assertEquals("Jackson", table.getCellValueFromTableWithHeader("1", "Last Name"));
        assertEquals("Doe", table.getCellValueFromTableWithHeader("John", "Last Name"));
        assertEquals("50", table.getCellValueFromTableWithHeader("Smith", "Points"));
        assertEquals("67", table.getCellValueFromTableWithHeader("Adam", "Points"));

        assertEquals("Jackson", table.getCellValueFromHeadlessTable("1", "Last Name"));
        assertEquals("Doe", table.getCellValueFromHeadlessTable("John", "Last Name"));
        assertEquals("50", table.getCellValueFromHeadlessTable("Smith", "Points"));
        assertEquals("67", table.getCellValueFromHeadlessTable("Adam", "Points"));
    }

    @Test
    public void shouldGetValuesBasedOnColumnIndex() {
        assertEquals("Doe", table.getCellValueFromTableWithHeader("2", 2));
        assertEquals("94", table.getCellValueFromTableWithHeader("Eve", 3));
        assertEquals("3", table.getCellValueFromTableWithHeader("Johnson", 0));
        assertEquals("Jill", table.getCellValueFromTableWithHeader("50", 1));

        assertEquals("Doe", table.getCellValueFromHeadlessTable("2", 2));
        assertEquals("94", table.getCellValueFromHeadlessTable("Eve", 3));
        assertEquals("3", table.getCellValueFromHeadlessTable("Johnson", 0));
        assertEquals("Jill", table.getCellValueFromHeadlessTable("50", 1));
    }

    @Test
    public void shouldGetValuesBasedOnRowIndex() {
        assertEquals("1", table.getCellValueFromTableWithHeader(1, "Number"));
        assertEquals("John", table.getCellValueFromTableWithHeader(2, "First Name"));
        assertEquals("Johnson", table.getCellValueFromTableWithHeader(3, "Last Name"));
        assertEquals("50", table.getCellValueFromTableWithHeader(4, "Points"));

        assertEquals("1", table.getCellValueFromHeadlessTable(1, "Number"));
        assertEquals("John", table.getCellValueFromHeadlessTable(2, "First Name"));
        assertEquals("Johnson", table.getCellValueFromHeadlessTable(3, "Last Name"));
        assertEquals("50", table.getCellValueFromHeadlessTable(4, "Points"));
    }

    @Test
    public void shouldGetValuesBasedOnRowAndColumnIndex() {
        assertEquals("1", table.getCellValueFromTableWithHeader(1, 0));
        assertEquals("John", table.getCellValueFromTableWithHeader(2, 1));
        assertEquals("Johnson", table.getCellValueFromTableWithHeader(3, 2));
        assertEquals("50", table.getCellValueFromTableWithHeader(4, 3));

        assertEquals("1", table.getCellValueFromHeadlessTable(1, 0));
        assertEquals("John", table.getCellValueFromHeadlessTable(2, 1));
        assertEquals("Johnson", table.getCellValueFromHeadlessTable(3, 2));
        assertEquals("50", table.getCellValueFromHeadlessTable(4, 3));
    }

    @Test
    public void shouldGetCellValueByTDID() {
        assertEquals("1", table.getTextfromTD());
        assertEquals("Eve", table.getTextfromSecondWithoutElementDiscoverExtFile());

    }

//    @Test
//    public void shouldVerifyJavascriptExecution() {
//        assertEquals("valor", table.myInputText.getAttribute("value"));
//        controller.runJavaScript("return changeInputTextValue(\"teste\", \"" + table.myInputText.getKeyValue() + "\");");
//        assertEquals("teste", table.myInputText.getAttributeValue());
//    }

    @Test
    public void clickMe() {
        table.clickMe();
    }

    @AfterClass
    public static void tearDown() {
        controller.quit();
    }

}
