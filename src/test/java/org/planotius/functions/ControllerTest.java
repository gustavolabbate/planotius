package org.planotius.functions;

import java.io.File;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.planotius.controller.Controller;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.planotius.helper.*;

/**
 *
 * @author ggodoy
 */
public class ControllerTest {

    private static final Logger LOG = Logger.getLogger(ControllerTest.class.getName());

    static Controller controller = new Controller();

    @BeforeClass
    public static void setUp() {
        String url = System.getProperty("user.dir") + "\\src\\test\\resources\\localTable.html";

        if (Config.getBrowser().equals("firefox")) {
            url = "file:" + System.getProperty("user.dir") + "\\src\\test\\resources\\localTable.html";
        }

        System.out.println("URL: " + url);
        Config.setUrl(url);
        controller.openUrl();

    }

    @Test
    public void shouldCaptureScreenshotEvidence() {
        LOG.info("Starting test: shouldCaptureScreenshotEvidence");
        String filename = "target/screenshotCaptured.png";

        controller.printScreen(filename);
        File screenshot = new File(filename);

        assertTrue(screenshot.exists());

    }

    @Test
    public void shouldOpenUrlAndGetPageTitle() {
        LOG.info("Starting test: shouldOpenUrlAndGetPageTitle");
        assertEquals("Test Page for Planotius!", controller.getPageTitle());
    }

    @Test
    public void shouldVerifyMessageOnPage() {
        LOG.info("Starting test: shouldVerifyMessageOnPage");
        assertTrue(controller.verifyMessage("Table with Header"));
    }

    @Test
    public void shouldSearchInHtmlContents() {
        LOG.info("Starting test: shouldSearchInHtmlContents");
        assertTrue(controller.searchHtmlContents("Headless Table"));
        assertTrue(controller.searchHtmlContents("<td>Number</td>"));
    }

//    @Test
//    public void shouldVerifyJavascriptExecution() {
//        assertEquals("teste", controller.runJavaScript("return changeInputTextValue(\"teste\", \"changedByjs\");"));
//    }
    @AfterClass
    public static void tearDown() {
        controller.quit();
    }
}
