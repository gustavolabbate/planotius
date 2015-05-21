package com.lenovo.functions.helper;

import com.lenovo.helper.CSVReader;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test the CSVReader Class
 *
 * @author ggodoy
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

    private static final Logger log = LoggerFactory.getLogger(ParameterizedTest.class.getName());

    @Parameterized.Parameters
    public static Collection<Object[]> loadParameters() {
        CSVReader csv = new CSVReader();
        return csv.read("src/test/resources/geoIp");
    }

    String ipComeco;
    String ipFim;
    String latitude;
    String longitude;
    String codigo;
    String pais;

    public ParameterizedTest(String ipComeco, String ipFim, String latitude, String longitude, String codigo, String pais) {

        this.ipComeco = ipComeco;
        this.ipFim = ipFim;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codigo = codigo;
        this.pais = pais;
    }

    /**
     * Should read a csv file
     */
    @Test
    public void shouldReadCSVFile() {
        System.out.println(ipComeco);
        System.out.println(ipFim);
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(codigo);
        System.out.println(pais);

    }
}
