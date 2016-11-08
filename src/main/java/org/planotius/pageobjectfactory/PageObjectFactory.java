package org.planotius.pageobjectfactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;
import org.planotius.controller.functions.FindBy;
import org.planotius.helper.PropertiesLoader;

/**
 *
 * @author ggodoy
 */
public class PageObjectFactory {

    private static final Logger LOG = Logger.getLogger(PageObjectFactory.class.getName());

    public <T> T init() {
        return (T) init(this.getClass());
    }

    private <T> T init(Class<T> aClass) {
        Class<?> poClass = this.getClass();
        T page = (T) aClass;

        try {
            Constructor<T> constructor = aClass.getConstructor();
            page = constructor.newInstance();
        } catch (NoSuchMethodException ex) {
            LOG.error(ex.getMessage(), ex);
        } catch (SecurityException ex) {
            LOG.error(ex.getMessage(), ex);
        } catch (InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            LOG.error(ex.getMessage(), ex);
        } catch (IllegalArgumentException ex) {
            LOG.error(ex.getMessage(), ex);
        } catch (InvocationTargetException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        Field[] fields = poClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Element element = loadnewInputData(aClass, field);

                if (element.getKey() != null) {
                    field.set(page, element);
                }
            } catch (IllegalArgumentException ex) {
                LOG.error(ex.getMessage(), ex);
            } catch (IllegalAccessException ex) {
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

                //Setting locator
                if (!myAnnotation.locator().equals("")) {
                    element.setLocator(myAnnotation.locator());
                }

                element.setAclass(aClass);
                element.setField(field);
            }
        }
        return element;
    }

    public static WebElement loadInputData(Element myElement) {
        String value;
        String locator;
        FindBy findBy = new FindBy(Controller.getDriver());

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

                locator = myAnnotation.locator();
                //[finish] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation

                /*if (myElement.getFrame() != "") {
                 try {
                 Controller.getDriver().switchTo().frame(myElement.getFrame());
                 } catch (Exception e) {
                 log.error(e.getMessage() + " BLABLABLABA");
                 }
                 }*/
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
            }
        }
        return null;
    }

    private static WebElement searchAll(FindBy findBy, String value) {
        WebElement element = null;
        element = findBy.id(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'id'.");
            return element;
        }

        element = findBy.className(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'className'.");
            return element;
        }

        element = findBy.tagName(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'tagName'.");
            return element;
        }

        try {
            element = findBy.name(value);
            if (element != null) {
                LOG.debug("Found '" + value + "'by 'name'.");
                return element;
            }
        } catch (Exception e) {
            LOG.debug("browser may have died when trying to findBy 'name'.");
        }

        element = findBy.linkText(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'linkText'.");
            return element;
        }

        element = findBy.partialLinkText(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'partialLinkText'.");
            return element;
        }

        try {
            element = findBy.cssSelector(value);
            if (element != null) {
                LOG.debug("Found '" + value + "'by 'css'.");
                return element;
            }
        } catch (Exception e1) {
            LOG.debug("browser may have died when trying to findBy 'CSS'.");
        }

        element = findBy.xpath(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'xpath'.");
            return element;
        }

        element = findBy.imageAlt(value);
        if (element != null) {
            LOG.debug("Found '" + value + "'by 'imageAlt'.");
            return element;
        }
        return null;
    }

}
