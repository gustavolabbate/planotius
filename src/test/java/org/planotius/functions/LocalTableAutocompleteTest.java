package org.planotius.functions;

import org.planotius.controller.Controller;
import org.planotius.pageobjects.LocalTable;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.planotius.helper.*;

/**
 *
 * @author ggodoy
 */
@Ignore
public class LocalTableAutocompleteTest {

    private static LocalTable table = new LocalTable().init();
    private static Controller controller = new Controller();

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("user.dir") + "\\src\\test\\resources\\localTable.html";
        
        if ("firefox".equals(Config.getBrowser())) {
            url = "file:" + System.getProperty("user.dir") + "\\src\\test\\resources\\localTable.html";
        } 

        
        Config.setUrl(url);
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
