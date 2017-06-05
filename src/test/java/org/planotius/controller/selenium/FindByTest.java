package org.planotius.controller.selenium;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.planotius.controller.Controller;
import org.planotius.helper.Config;

/**
 *
 * @author ggodoy
 */
public class FindByTest {

    static Controller controller = new Controller();
    static FindBy findBy = new FindBy(Controller.getDriver());
    private WebElement webElement;

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("user.dir") + "\\src\\test\\resources\\localTable.html";

        if (Config.getBrowser().equals("firefox")) {
            url = "file:" + System.getProperty("user.dir") + "\\src\\test\\resources\\localTable.html";
        }

        Config.setUrl(url);
        controller.openUrl();
    }

    @Before
    public void betweenTest() {
        webElement = null;
    }

    @Test
    public void testFindByID() {
        webElement = findBy.id("complete");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("browsers", webElement.getAttribute("list"));
    }

    @Test
    public void testFindByImageAlt() {
        webElement = findBy.imageAlt("octopus");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("octopus", webElement.getAttribute("alt"));
    }

    @Test
    public void testFindByPartialLinkText() {
        webElement = findBy.partialLinkText("nowhere");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("This is a link to nowhere", webElement.getText());
    }

    @Test
    public void testFindByXpath() {
        webElement = findBy.xpath("html/body/div[1]/input[3]");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("use xpath here", webElement.getAttribute("value"));
    }

    @Test
    public void testFindBylinkText() {
        webElement = findBy.linkText("Another link");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("Another link", webElement.getText());
    }

    @Test
    public void testFindByClassName() {
        webElement = findBy.className("myClassName");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("use className here", webElement.getAttribute("value"));
    }

    @Test
    public void testFindByCssSelector() {
        webElement = findBy.cssSelector("input[name=\"useCSSselector\"]");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("use css here", webElement.getAttribute("value"));
    }

    @Test
    public void testFindByTagName() {
        webElement = findBy.tagName("img");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("octopus", webElement.getAttribute("alt"));
    }

    @Test
    public void testFindByName() {
        webElement = findBy.name("this_name");
        Assert.assertNotNull(webElement);
        Assert.assertEquals("using name", webElement.getAttribute("value"));
    }

    /*
    Tests to when element is not there...
     */
    @Test
    public void testShouldNotFindByImageAltAndShouldReturnNull() {
        webElement = findBy.imageAlt("octopusNotExistent");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByIDAndShouldReturnNull() {
        webElement = findBy.id("completeNotExist");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByPartialLinkTextAndShouldReturnNull() {
        webElement = findBy.partialLinkText("nowhereNotExist");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByXpathAndShouldReturnNull() {
        webElement = findBy.xpath("html/body/div[1]/input[3]NotExist");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindBylinkTextAndShouldReturnNull() {
        webElement = findBy.linkText("NotExist");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByClassNameAndShouldReturnNull() {
        webElement = findBy.className("myClassNameNotExist");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByCssSelectorAndShouldReturnNull() {
        webElement = findBy.cssSelector("input[name=\"useCSSselectorNotExist\"]");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByTagNameAndShouldReturnNull() {
        webElement = findBy.tagName("imgNotExist");
        Assert.assertNull(webElement);
    }

    @Test
    public void testShouldNotFindByNameAndShouldReturnNull() {
        webElement = findBy.name("this_nameNotExist");
        Assert.assertNull(webElement);
    }

    @AfterClass
    public static void tearDown() {
        controller.quit();
    }

}
