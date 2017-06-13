package org.planotius.functions.helper;

import org.planotius.helper.CSVReader;
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

    private static final Logger LOG = LoggerFactory.getLogger(ParameterizedTest.class.getName());

    /**
     * Default constructor
     *
     * @param ipComeco
     * @param ipFim
     * @param latitude
     * @param longitude
     * @param codigo
     * @param pais
     */
    public ParameterizedTest(String ipComeco, String ipFim, String latitude,
            String longitude, String codigo, String pais) {

        this.ipComeco = ipComeco;
        this.ipFim = ipFim;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codigo = codigo;
        this.pais = pais;
    }

    /**
     * Default reader of parameters
     *
     * @return
     */
    @Parameterized.Parameters
    public static Collection<Object[]> loadParameters() {
        CSVReader csv = new CSVReader();
        return csv.read("src/test/resources/geoIp");
    }

    private String ipComeco;
    private String ipFim;
    private String latitude;
    private String longitude;
    private String codigo;
    private String pais;

    /**
     * Should read a csv file
     */
    @Test
    public void shouldReadCSVFile() {
        LOG.info(ipComeco);
        LOG.info(ipFim);
        LOG.info(latitude);
        LOG.info(longitude);
        LOG.info(codigo);
        LOG.info(pais);

    }
}
