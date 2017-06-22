package org.planotius.functions.helper;

import org.planotius.helper.CSVReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test the CSVReader Class
 *
 * @author ggodoy
 */
public class CSVReaderTest {
    private static final Logger LOG = LoggerFactory.getLogger(CSVReaderTest.class.getName());

    /**
     * Should read a csv file
     */
    @Test
    public void shouldReadCSVFile() {
        try {
            CSVReader csv = new CSVReader();
            Collection<Object[]> geoIp = csv.read("src/test/resources/geoIp");
           
            for (Object[] objects : geoIp) {
                for (Object object : objects) {
                    String value = (String) object;
                    assertNotNull(value);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            fail(e.getMessage());
            
        }


    }

    /**
     * Should read a csv file and return a List of HashMap
     */
    @Test
    public void shouldReturnAHashMap() {
        try {
            CSVReader csv = new CSVReader();
            List<Map<String, String>> geoIp = csv.getList("src/test/resources/geoIp");

            for (Map<String, String> map : geoIp) {
                assertNotNull(map.get("ipComeco"));
                assertNotNull(map.get("ipFim"));
                assertNotNull(map.get("latitude"));
                assertNotNull(map.get("longitude"));
                assertNotNull(map.get("codigo"));
                assertNotNull(map.get("pais"));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            fail(e.getMessage());
            
        }


    }
}
