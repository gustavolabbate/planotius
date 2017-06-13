package org.planotius.functions;

import org.planotius.helper.PropertiesLoader;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the LoadProperties Class
 *
 * @author ggodoy
 */

public class PropertiesLoaderTest {

    /**
     * This test open a file from fileSystem and get a value
     */
    @Test
    public void shouldOpenFromFileSystemAndGetValue() {

        PropertiesLoader properties = new PropertiesLoader("src/test/resources/myCustomProp.properties");
        String returnedValue = properties.getValue("opened.from.filesystem");
        assertEquals("this property came from filesystem", returnedValue);
    }

    /**
     * This test open a file from classpath and get a value
     */
    @Test
    public void shouldOpenFromClasspathAndGetValue() {
        PropertiesLoader properties = new PropertiesLoader();

        String returnedValue = properties.getValue("opened.from.classpath");
        assertEquals("this property came from classpath", returnedValue);
    }

    @Test
    public void shouldOverwriteWithEnvironmentoVariables() {
        System.setProperty("config.properties", "src/test/resources/myCustomProp.properties");
        PropertiesLoader properties = new PropertiesLoader();
        String returnedValue = properties.getValue("opened.from.filesystem");
        assertEquals("this property came from filesystem", returnedValue);

        System.setProperty("opened.from.filesystem", "This property has changed...");

        returnedValue = properties.getValue("opened.from.filesystem");
        System.clearProperty("opened.from.filesystem");
        assertEquals("This property has changed...", returnedValue);
    }
    
    @AfterClass
    public static void after(){
        System.setProperty("config.properties", "");
    }
    
}
