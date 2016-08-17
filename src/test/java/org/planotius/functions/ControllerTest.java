package org.planotius.functions;

import java.io.File;
import org.junit.AfterClass;
import org.junit.Test;
import org.planotius.controller.Controller;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author ggodoy
 */
public class ControllerTest {

    static Controller controller = new Controller();
    static String url = "file:" + System.getProperty("user.dir") + "/src/test/resources/localTable.html";

    @BeforeClass
    public static void setUp() {
        controller.setUrl(url).openUrl();
    }

    @Test
    public void shouldCaptureScreenshotEvidence() {
        String filename = "target/screenshotCaptured.png";

        controller.printScreen(filename);
        File screenshot = new File(filename);

        assertTrue(screenshot.exists());

    }

    @Test
    public void shouldOpenUrlAndGetPageTitle() {
        assertEquals("Test Page for Planotius!", controller.getPageTitle());
    }

    @Test
    public void shouldVerifyMessageOnPage() {
        assertTrue(controller.verifyMessage("Table with Header"));
    }

    @Test
    public void shouldSearchInHtmlContents() {
        assertTrue(controller.searchHtmlContents("Headless Table"));
        assertTrue(controller.searchHtmlContents("<td>Number</td>"));
    }

    @Test
    public void shouldVerifyJavascriptExecution() {
        assertEquals("teste", controller.runJavaScript("return changeInputTextValue(\"teste\", \"changedByjs\");"));
    }

    @AfterClass
    public static void tearDown() {
        controller.quit();
    }
}
