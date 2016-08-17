package org.planotius.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;
import org.planotius.controller.functions.FindBy;
import org.planotius.helper.PropertiesLoader;

/**
 *
 * @author ggodoy
 */
public class PageObjectFactory {

    private static final Logger log = Logger.getLogger(PageObjectFactory.class.getName());

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

    public static WebElement loadInputData(Element myElement) {
        String value;
        WebElement element = null;
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
                //[finish] [TAM-3] Skip the external file. You can set the value directly on the ElementDiscover annotation

                /*if (myElement.getFrame() != "") {
                 try {
                 Controller.getDriver().switchTo().frame(myElement.getFrame());
                 } catch (Exception e) {
                 log.error(e.getMessage() + " BLABLABLABA");
                 }
                 }*/
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

}
