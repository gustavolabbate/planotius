package org.planotius.functions;

import org.planotius.controller.Controller;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.planotius.helper.Config;

/**
 *
 * @author ggodoy
 */
public class OpenGoogleTest {

    static Controller controller = new Controller();

    @BeforeClass
    public static void setup() {
        Config.setUrl("http://www.google.com");
        controller.openUrl();

    }

    @Test
    public void shouldVerifyTextOnPage(){
        controller.printScreen("target/googlepageOpened.png");
        assertEquals("Google", controller.getPageTitle());
    }
    
    
    @AfterClass
    public static void tearDown() {
        controller.quit();
    }

}
