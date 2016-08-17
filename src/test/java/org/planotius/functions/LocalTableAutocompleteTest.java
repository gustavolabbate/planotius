package org.planotius.functions;

import org.planotius.controller.Controller;
import org.planotius.pageobjects.LocalTable;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author ggodoy
 */
@Ignore
public class LocalTableAutocompleteTest {

    static LocalTable table;
    static Controller controller = new Controller();

    @BeforeClass
    public static void setup() {
        table = new LocalTable().init();
        controller.setUrl("file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html");
        controller.openUrl();
    }

    @Test
    public void shouldSelectFirefoxOnAutocompleteField() {
        assertEquals("Firefox", table.isValueInAutocompleteField("Fire",1));
        
    }
    
    @Test
    public void shouldSelectSafariOnAutocompleteField() {

        assertEquals("Safari", table.isValueInAutocompleteField("Saf",2));
        
    }

    @AfterClass
    public static void tearDown() {
        controller.quit();
    }

}
