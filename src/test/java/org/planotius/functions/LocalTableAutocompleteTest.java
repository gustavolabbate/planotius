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
public class LocalTableAutocompleteTest {

    static LocalTable table;

    @BeforeClass
    public static void setup() {
        table = new LocalTable().init();
        table.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        table.openUrl();
    }

    @Test
    public void shouldSelectFirefoxOnAutocompleteField() {

        assertEquals("Firefox", table.isValueInAutocompleteField("F",1));
        
    }
    
    @Test
    public void shouldSelectSafariOnAutocompleteField() {

        assertEquals("Safari", table.isValueInAutocompleteField("F",2));
        
    }

    @AfterClass
    public static void tearDown() {
        Controller.stop();
    }

}
