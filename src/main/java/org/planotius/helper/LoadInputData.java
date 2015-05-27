package org.planotius.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author ggodoy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LoadInputData {
    
    /**
     * load a file on classpath or by complete path
     * @return 
     */
    String filename();
}
