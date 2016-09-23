package org.planotius.functions;

import org.planotius.controller.Controller;
import org.planotius.pageobjects.LocalTable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.planotius.helper.Config;

/**
 *
 * @author ggodoy
 */
public class LocalTableTestSubmit {

    static LocalTable table;
    static Controller controller = new Controller();

    @BeforeClass
    public static void setup() {
        table = new LocalTable().init();

        Config.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        controller.openUrl();
    }

    @Test
    public void shouldClickSubmitButton() {
        table.clickMe();
        assertEquals("button clicked!", table.textFieldforButton.getAttributeValue());
        assertEquals("Jackson", table.getCellValueFromTableWithHeader("1", "Last Name"));

    }

    @AfterClass
    public static void tearDown() {
        controller.quit();
    }

}
