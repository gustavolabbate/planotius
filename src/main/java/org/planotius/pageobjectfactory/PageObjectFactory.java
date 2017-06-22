package org.planotius.pageobjectfactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.planotius.controller.Controller;
import org.planotius.controller.selenium.Element;
import org.planotius.controller.selenium.ElementDiscover;
import org.planotius.controller.selenium.FindBy;
import org.planotius.helper.PropertiesLoader;

/**
 *
 * @author ggodoy
 */
public class PageObjectFactory {

    private static final Logger LOG = Logger.getLogger(PageObjectFactory.class.getName());

    /**
     * Init the page object
     *
     * @param <T>
     * @return
     */
    public <T> T init() {
        return (T) init(this.getClass());
    }

    private <T> T init(Class<T> aClass) {
        Class<?> poClass = this.getClass();
        T page = (T) aClass;

        /*
        Get the constructor from the Page Object Class.
         */
        try {
            Constructor<T> constructor = aClass.getConstructor();
            page = constructor.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        /*
        Get all fields from page object class that contains the @ElementDiscover annotation
         */
        Field[] fields = poClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Element element = loadnewInputData(aClass, field);

                if (element.getKey() != null) {
                    field.set(page, element);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return page;
    }

    private Element loadnewInputData(Class aClass, Field field) {
        Element element = new Element();

        String interfaceMapName = aClass.getSimpleName();

        field.setAccessible(true);
        Annotation[] annotations = field.getDeclaredAnnotations();

        /*
        For each field, check if the annotation is from the type of ElementDiscover 
        and set the key, key value the class and the field to the element. 
         */
        for (Annotation annotation : annotations) {

            if (annotation instanceof ElementDiscover) {
                ElementDiscover myAnnotation = (ElementDiscover) annotation;
                /*
                [init] [TAM-3] Skip the external file. 
                You can set the value directly on the ElementDiscover annotation
                 */
                if ("".equals(myAnnotation.key())) {
                    element.setKeyValue(myAnnotation.value());
                    element.setKey(myAnnotation.value());
                } else {
                    PropertiesLoader map = new PropertiesLoader(interfaceMapName);
                    element.setKey(myAnnotation.key());
                    element.setKeyValue(map.getValue(myAnnotation.key()));
                }
                /*
                [finish] [TAM-3] Skip the external file. 
                You can set the value directly on the ElementDiscover annotation
                 */
                if (!"".equals(myAnnotation.frame())) {
                    element.setFrame(myAnnotation.frame());
                }

                //Setting locator
                if (!"".equals(myAnnotation.locator())) {
                    element.setLocator(myAnnotation.locator());
                }

                element.setAclass(aClass);
                element.setField(field);
            }
        }
        return element;
    }

    /**
     * Load all Elements from annotated fields.
     *
     * @param myElement
     * @return
     */
    public static WebElement loadInputData(Element myElement) {
        String value = "";
        String locator = "";

        String interfaceMapName = myElement.getAclass().getSimpleName();
        Annotation[] annotations = myElement.getField().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ElementDiscover) {
                ElementDiscover myAnnotation = (ElementDiscover) annotation;

                /*[init] [TAM-3] Skip the external file. 
                You can set the value directly on the ElementDiscover annotation
                 */
                if ("".equals(myAnnotation.key())) {
                    value = myAnnotation.value();
                } else {
                    PropertiesLoader map = new PropertiesLoader(interfaceMapName);
                    value = map.getValue(myAnnotation.key());
                }

                locator = myAnnotation.locator();
                //[finish] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation
            }
        }
        return getWebelementByLocator(value, locator);
    }

    private static WebElement getWebelementByLocator(String value, String locator) {
        FindBy findBy = new FindBy(Controller.getDriver());
        if (value != null) {
            switch (locator.toLowerCase()) {
                case "id":
                    LOG.debug("Using 'id' to find '" + value + "'.");
                    return findBy.id(value);
                case "name":
                    LOG.debug("Using 'name' to find '" + value + "'.");
                    return findBy.name(value);
                case "partiallinktest":
                    LOG.debug("Using 'partialLinkText' to find '" + value + "'.");
                    return findBy.partialLinkText(value);
                case "xpath":
                    LOG.debug("Using 'xpath' to find '" + value + "'.");
                    return findBy.xpath(value);
                case "css":
                    LOG.debug("Using 'css' to find '" + value + "'.");
                    return findBy.cssSelector(value);
                case "linktext":
                    LOG.debug("Using 'linkText' to find '" + value + "'.");
                    return findBy.linkText(value);
                case "tag":
                    LOG.debug("Using 'tagName' to find '" + value + "'.");
                    return findBy.tagName(value);
                case "class":
                    LOG.debug("Using 'className' to find '" + value + "'.");
                    return findBy.className(value);
                case "imageAlt":
                    LOG.debug("Using 'imageAlt' to find '" + value + "'.");
                    return findBy.imageAlt(value);
                default:
                    LOG.debug("Locator undefined, trying to search a locator for '" + value + "'.");
                    return searchAll(findBy, value);
            }
        }
        return null;
    }

    private static WebElement searchAll(FindBy findBy, String value) {
        WebElement element = null;
        element = findBy.id(value);
        if (element != null) {
            return element;
        }

        element = findBy.className(value);
        if (element != null) {
            return element;
        }

        element = findBy.tagName(value);
        if (element != null) {
            return element;
        }

        try {
            element = findBy.name(value);
            if (element != null) {
                return element;
            }
        } catch (Exception e) {
            LOG.debug("browser may have died when trying to findBy 'name'.");
        }

        element = findBy.linkText(value);
        if (element != null) {
            return element;
        }

        element = findBy.partialLinkText(value);
        if (element != null) {
            return element;
        }

        try {
            element = findBy.cssSelector(value);
            if (element != null) {
                return element;
            }
        } catch (Exception e1) {
            LOG.debug("browser may have died when trying to findBy 'CSS'.");
        }

        element = findBy.xpath(value);
        if (element != null) {
            return element;
        }

        element = findBy.imageAlt(value);
        if (element != null) {
            return element;
        }
        return null;
    }

}
