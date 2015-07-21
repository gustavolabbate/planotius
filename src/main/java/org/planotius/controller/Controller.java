package org.planotius.controller;

import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.FindBy;
import org.planotius.controller.functions.ElementDiscover;
import org.planotius.controller.functions.SeleniumServer;
import org.planotius.helper.Config;
import org.planotius.helper.PropertiesLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author ggodoy
 */
public abstract class Controller {

    private static final Logger log = Logger.getLogger(Controller.class.getName());
    private static SeleniumServer server;
    private static WebDriver driver;
    private String browser;
    private String testServer;
    private String port;
    private String url;
    public static Config config;

    public void setUrl(String url) {
        this.url = url;
    }

    public Config getConfig() {
        return config;
    }

    public SeleniumServer getServer() {
        return server;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public <T> T init() {
        return (T) PageFactory.initElements(server.getDriver(), this.getClass());
    }

    public <T> T init(Class<T> aClass) {
        Class<?> poClass = this.getClass();
        T page = (T) aClass;

        try {
            Constructor<T> constructor = aClass.getConstructor();
            page = constructor.newInstance();
        } catch (NoSuchMethodException ex) {
            log.error(ex.getMessage(), ex);
        } catch (SecurityException ex) {
            log.error(ex.getMessage(), ex);
        } catch (InstantiationException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
        } catch (InvocationTargetException ex) {
            log.error(ex.getMessage(), ex);
        }

        Field[] fields = poClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Element element = loadnewInputData(aClass, field);

                if (element.getKey() != null) {
                    field.set(page, element);
                }
            } catch (IllegalArgumentException ex) {
                log.error(ex.getMessage(), ex);
            } catch (IllegalAccessException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return page;
    }

    private Element loadnewInputData(Class aClass, Field field) {
        Element element = new Element();

        String interfaceMapName = aClass.getSimpleName();

        field.setAccessible(true);
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {

            if (annotation instanceof ElementDiscover) {
                ElementDiscover myAnnotation = (ElementDiscover) annotation;

                //[init] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation
                if (myAnnotation.key().equals("")) {
                    element.setKeyValue(myAnnotation.value());
                    element.setKey(myAnnotation.value());
                } else {
                    PropertiesLoader map = new PropertiesLoader(interfaceMapName);
                    element.setKey(myAnnotation.key());
                    element.setKeyValue(map.getValue(myAnnotation.key()));
                }
                //[finish] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation

                if (!myAnnotation.frame().equals("")) {
                    element.setFrame(myAnnotation.frame());
                }

                element.setAclass(aClass);
                element.setField(field);
            }
        }
        return element;
    }

    public WebElement loadInputData(Element myElement) {
        String value;
        WebElement element = null;
        FindBy findBy = new FindBy(server.getDriver());

        String interfaceMapName = myElement.getAclass().getSimpleName();

        Annotation[] annotations = myElement.getField().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ElementDiscover) {
                ElementDiscover myAnnotation = (ElementDiscover) annotation;

                //[init] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation
                if (myAnnotation.key().equals("")) {
                    value = myAnnotation.value();
                } else {
                    PropertiesLoader map = new PropertiesLoader(interfaceMapName);
                    value = map.getValue(myAnnotation.key());
                }
                //[finish] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation

                if (myElement.getFrame() != "") {
                    //TODO: Adjust Frame navigation (This is a temporary solution...
                    try {
                        driver.switchTo().frame(myElement.getFrame());
                    } catch (Exception e) {
                        
                    }
                }

                if (value != null) {

                    element = findBy.id(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.name(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.partialLinkText(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.xpath(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.cssSelector(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.linkText(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.tagName(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.className(value);
                    if (element != null) {
                        return element;
                    }

                    element = findBy.imageAlt(value);
                    if (element != null) {
                        return element;
                    }

                }
            }
        }
        return null;
    }

    public Controller() {
        PropertiesLoader properties = new PropertiesLoader();
        this.browser = properties.getValue("browser");

        if (System.getProperty("browser") != null) {
            this.browser = System.getProperty("browser");
        } else {
            this.browser = properties.getValue("browser");
        }

        if (System.getProperty("testserver") != null) {
            this.testServer = System.getProperty("testserver");
        } else {
            this.testServer = properties.getValue("testserver");
        }

        if (System.getProperty("port") != null) {
            this.port = System.getProperty("port");
        } else {
            this.port = properties.getValue("port");
        }

        if (System.getProperty("url") != null) {
            this.url = System.getProperty("url");
        } else {
            this.url = properties.getValue("url");
        }

        connectServer();
    }

    public void connectServer() {
        server = new SeleniumServer(browser, testServer, port);
        driver = server.getDriver();
    }

    public String getAlertMessage() {
        try {
            Alert alert = server.getDriver().switchTo().alert();
            String alertMessage = alert.getText();
            return alertMessage;
        } catch (Exception e) {
            return "no alert found.";
        }
    }

    public String clickAlert() {
        try {
            Alert alert = server.getDriver().switchTo().alert();
            String msg = alert.getText();
            alert.accept();
            return msg;
        } catch (Exception e) {
            log.info("no alert found.");
            return null;
        }
    }

    public Object runJavaScript(String function) {
        try {
            return ((JavascriptExecutor) getDriver()).executeScript(function);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return e;
        }

    }

    /**
     * PrintScreen and save with the desired file name.
     *
     * @param fileName
     * @return fileName
     */
    public String printScreen(String fileName) {
        try {
            if (this.server.getTestServer().equalsIgnoreCase("localhost")) {
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                byte[] decodedScreenshot = Base64.decodeBase64(base64Screenshot.getBytes());
                FileOutputStream fos = new FileOutputStream(new File(fileName));
                fos.write(decodedScreenshot);
                fos.close();

                File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            } else {
                File f = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
                File o = new File(fileName);
                FileUtils.copyFile(f, o);
            }
            log.info("imagem capturada do servidor '" + server.getTestServer() + "' em: '" + fileName + "'");
            return fileName;
        } catch (Exception e) {
            log.error(e.getMessage() + " Erro ao capturar imagem do servidor '" + server.getTestServer() + "' em: '" + fileName + "'", e);
            return null;
        }
    }

    public boolean verifyMessage(String texto) {
        boolean b = searchHtmlContents(texto);
        return b;
    }

    /**
     * Open the desired url
     *
     */
    public void openUrl() {
        driver.get(this.url.replace("\"", ""));
        driver.manage().window().maximize();
        log.info("acessou a url: '" + this.url.replace("\"", "") + "'");
    }

    /**
     * Search in the current html for the desired text
     *
     * @param text
     * @return
     */
    public boolean searchHtmlContents(String text) {
        boolean exist = driver.getPageSource().contains(text);
        log.info("Texto : '" + text + "' encontrado? " + exist);

        return exist;

    }

    public static void stop() {
        SeleniumServer.stopServer();
    }

    public String getTestServer() {
        return testServer;
    }

    public String getBrowser() {
        return browser;
    }

    public String getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }
}
