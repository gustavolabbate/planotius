package org.planotius.functions;

import org.planotius.controller.Controller;
import org.planotius.pageobjects.LocalTable;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.planotius.helper.Config;

/**
 *
 * @author ggodoy
 */
public class LocalTableTestTD {

    LocalTable table;
    static Controller controller = new Controller();

    public LocalTableTestTD() {
        table = new LocalTable().init();
    }

    @Test
    public void openSensorMonitor() {
        Config.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        controller.openUrl();

        assertEquals("1", table.getTextfromTD());
    }

    @AfterClass
    public static void tearDown() {
        controller.quit();
    }

}
