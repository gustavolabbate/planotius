package org.planotius.functions;

import org.planotius.controller.Controller;
import org.planotius.pageobjects.LocalTable;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ggodoy
 */
public class LocalTableTestTD {

    LocalTable table;

    public LocalTableTestTD() {
        table = new LocalTable().init(LocalTable.class);
    }

    @Test
    public void openSensorMonitor() {

        table.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        table.openUrl();

        System.out.println(table.getTextfromTD());
    }

    @AfterClass
    public static void tearDown() {
        Controller.stop();
    }

}
