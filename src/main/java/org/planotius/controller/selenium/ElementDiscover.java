package org.planotius.controller.selenium;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author ggodoy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ElementDiscover {
    String value();
    String key() default "";
    String frame() default "";  
    String locator() default "";
    
    
}
