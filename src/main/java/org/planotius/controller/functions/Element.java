package org.planotius.controller.functions;

import org.planotius.controller.Controller;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.planotius.helper.Config;
import org.planotius.pageobjectfactory.PageObjectFactory;

/**
 * @author ggodoy
 */
public class Element implements WebElement {

    private static final Logger LOG = Logger.getLogger(Element.class.getName());

    String key;
    String keyValue;
    String frame;
    String locator;
    public WebElement webElement;
    Class aclass;
    Field field;

    private static long implicitWait;
    private static long pageLoadTimeout;
    private static long explicitWait;

    private static final long DEFAULT_IMPLICIT_WAIT = 0;
    private static final long DEFAULT_EXPLICIT_WAIT = 0;
    private static final long DEFAULT_PAGE_LOAD_TIMEOUT = 30;

    static {
        implicitWait = ((Config.getConfiguration("implicitWait") == null) ? DEFAULT_IMPLICIT_WAIT :
                Long.valueOf(Config.getConfiguration("implicitWait")));
        explicitWait = ((Config.getConfiguration("explicitWait") == null) ? DEFAULT_EXPLICIT_WAIT :
                Long.valueOf(Config.getConfiguration("explicitWait")));
        pageLoadTimeout = (Config.getConfiguration("pageLoadTimeout") == null) ? DEFAULT_PAGE_LOAD_TIMEOUT :
                Long.valueOf(Config.getConfiguration("pageLoadTimeout"));
    }

    public Element(WebElement element) {
        this.webElement = element;
    }

    public Element() {
    }

    /**
     * Get the attribute 'keyValue' of an WebElement. The same of
     * WebElement.getAttribute("keyValue");
     *
     * @return
     */
    public String getAttributeValue() {
        reload();
        return this.webElement.getAttribute("value");
    }

    /**
     * Returns the value set to the key, on the element, key=value in a
     * property.
     *
     * @return
     */
    public String getKeyValue() {
        return keyValue;
    }

    /**
     * Set the value to the key, on the element, key=value in a property.
     *
     * @param value
     */
    public void setKeyValue(String value) {
        this.keyValue = value;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Class getAclass() {
        return aclass;
    }

    public Field getField() {
        return field;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    private void reload() {
        if (this.webElement == null) {
            try {
                this.webElement.isDisplayed();
            } catch (StaleElementReferenceException | NullPointerException stale) {
//                this.webElement = null;
                LOG.debug("Reloading the object because : [" + stale.getMessage() + "]");
                this.webElement = PageObjectFactory.loadInputData(this);
            }
        }
    }

    @Override
    public void click() {
        reload();
        try {
            this.webElement.click();
        } catch (Exception e) {
            LOG.error("Can not click on [" + this.webElement.getAttribute("value") + "] : " + e.getMessage());
        }
        waitPageLoad();
    }

    public void clickNoWait() {
        reload();
        this.webElement.click();
    }

    @Override
    public void submit() {
        reload();
        this.webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... css) {
        reload();
        this.webElement.clear();
        this.webElement.sendKeys(css);
    }

    public void selectOnlist(Object value) {
        reload();
        waitCurrentPageLoad();
        List<WebElement> options = this.webElement.findElements(By.tagName("option"));

        if (value instanceof String) {

            if (!value.equals("")) {
                for (WebElement option : options) {
                    if (option.getText().equals(value)) {
                        option.click();
                        return;
                    }
                }
            }
        } else if (value instanceof Integer) {
            int index = (Integer) value;
            options.get(index).click();
        }
    }

    @Override
    public void clear() {
        reload();
        this.webElement.clear();
    }

    @Override
    public String getTagName() {
        reload();
        return this.webElement.getTagName();
    }

    @Override
    public String getAttribute(String string) {
        reload();
        return this.webElement.getAttribute(string);
    }

    @Override
    public boolean isSelected() {
        reload();
        return this.webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        reload();
        return this.webElement.isEnabled();
    }

    @Override
    public String getText() {
        reload();
        return this.webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        reload();
        return this.webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        reload();
        return this.webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        reload();

        if (this.webElement == null) {
            return false;
        } else {
            return this.webElement.isDisplayed();
        }
    }

    @Override
    public Point getLocation() {
        reload();
        return this.webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        reload();
        return this.webElement.getSize();
    }

    @Override
    public String getCssValue(String string) {
        reload();
        return this.webElement.getCssValue(string);
    }

    private void waitCurrentPageLoad() {
        try {
            Controller.getDriver().manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            Controller.getDriver().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.MILLISECONDS);
            Thread.sleep(explicitWait);
        } catch (Exception e) {
            LOG.error(e.getMessage());

        }
    }

    public boolean waitElementDisplayed() {
        try {
            while (this.webElement == null) {
                reload();
            }
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * Cause a webDriver wait until all elements is visible.
     */
    public void waitPageLoad() {
        try {
            Calendar init = Calendar.getInstance();
            waitCurrentPageLoad();
            Calendar finish = Calendar.getInstance();
            LOG.info(Controller.getDriver().getCurrentUrl() + " took  " + (finish.getTimeInMillis() - init.getTimeInMillis()) + " ms. to load.");
        } catch (Exception e) {
            Controller controller = new Controller() {
            };
            File file = new File("target/CapturedErrors/");
            if (!file.exists()) {
                file.mkdir();
            }
            controller.printScreen("target/CapturedErrors/waitPageLoad" + e.getMessage() + "-" + Calendar.getInstance() + ".png");
        }
    }

    /**
     * Return a cell keyValue based on provided parameters for column and row.
     * For row and columns, inform String or Integer types.
     *
     * @param lookupRow
     * @param lookupColumn
     * @return
     */
    public String getCellValueFromTable(Object lookupRow, Object lookupColumn) {
        try {
            reload();

            List<WebElement> lines = this.findElements(By.tagName("tr"));

            int rowNum, colNum, headerNum;
            rowNum = 0;

            ArrayList<String> columns = new ArrayList<String>();
            ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();

            int lookupColumIndex = 0;
            int lookupRowIndex = 0;
            boolean hasHeader = false;

            for (WebElement line : lines) {
                colNum = 0;
                headerNum = 0;

                List<WebElement> cols = line.findElements(By.xpath("td"));

                List<WebElement> headers = line.findElements(By.tagName("th"));
                for (WebElement header : headers) {
                    if (lookupColumn instanceof String) {
                        if (header.getText().equals(lookupColumn)) {
                            lookupColumIndex = headerNum;
                            hasHeader = true;
                        }
                    } else if (lookupColumn instanceof Integer) {
                        lookupColumIndex = (Integer) lookupColumn;
                    } else {
                        return null;
                    }

                    headerNum++;
                }
                for (WebElement col : cols) {
                    columns.add(col.getText());

                    if (!hasHeader) {
                        if (lookupColumn instanceof String) {
                            if (col.getText().equals(lookupColumn)) {
                                lookupColumIndex = colNum;
                            }
                        } else if (lookupColumn instanceof Integer) {
                            lookupColumIndex = (Integer) lookupColumn;
                        } else {
                            return null;
                        }
                    }

                    if (lookupRow instanceof String) {
                        if (col.getText().equals(lookupRow)) {
                            lookupRowIndex = rowNum;
                        }
                    } else if (lookupRow instanceof Integer) {
                        lookupRowIndex = (Integer) lookupRow;
                    } else {
                        return null;
                    }

                    colNum++;
                }
                rows.add(columns);
                columns = new ArrayList<String>();
                rowNum++;
            }

            return rows.get(lookupRowIndex).get(lookupColumIndex);

        } catch (StaleElementReferenceException e) {
            return getCellValueFromTable(lookupRow, lookupColumn);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Rectangle getRect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> ot) throws WebDriverException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
