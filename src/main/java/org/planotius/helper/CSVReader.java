package org.planotius.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * Use to read an CSV file.
 *
 * @author gustavolabbate
 */
public class CSVReader {

    private static final Logger LOG = Logger.getLogger(CSVReader.class.getName());

    private static String delimiter;
    private static String encoding;

    public CSVReader() {
        CSVReader.delimiter = Config.getConfiguration("csv.delimiter");

        if (CSVReader.delimiter == null) {
            this.delimiter = ",";
        }

        CSVReader.encoding = Config.getConfiguration("csv.encoding");

        if (CSVReader.encoding == null) {
            CSVReader.encoding = "UTF-8";
        }

    }

    /**
     * Read a CSV file and return a collection of Objects, with no headers
     * (remove first line). This collection will be used in Junit Parameters.
     *
     * @param file String
     * @return Collection Object[]
     */
    public Collection<Object[]> read(String file) {
        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), CSVReader.encoding));

            Collection<Object[]> rows = createCollection(br);

            for (Object[] objects : rows) {
                rows.remove(objects);
                break;
            }

            return rows;
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Read a CSV file and return a list of Map. This collection will be used in
     * InputData Class from testFramework.
     *
     * @param file
     * @return
     */
    public List<Map<String, String>> getList(String file) {
        List<String[]> rows = (List) openCSV(file);
        Map<String, String> map = new HashMap<String, String>();
        ArrayList<Map<String, String>> listMap = new ArrayList<Map<String, String>>();

        for (int x = 1; x < rows.size(); x++) {

            for (int y = 0; y < rows.get(x).length; y++) {
                map.put(rows.get(0)[y],
                        rows.get(x)[y]);
            }
            listMap.add(map);
            map = new HashMap<String, String>();
        }

        return listMap;
    }

    private Collection<Object[]> openCSV(BufferedReader bufferedReader) {

        return createCollection(bufferedReader);
    }

    private Collection<Object[]> openCSV(String file) {
        InputStream stream = null;
        try {

            try {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                stream = loader.getResourceAsStream(file);
            }

            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(stream, CSVReader.encoding));
            } catch (NullPointerException npe) {
                LOG.error("The file '" + file + "' was not found. Check if exists or is acessible.", npe);
            }

            return createCollection(br);

        } catch (UnsupportedEncodingException ex) {
            LOG.error("Encoding error.", ex);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    LOG.error("IO Exception", ex);
                }
            }
        }

        return null;
    }

    private Collection<Object[]> createCollection(BufferedReader br) {
        String line = "";
        try {
            List<Object[]> rows = new ArrayList<Object[]>();
            while ((line = br.readLine()) != null) {

                //Allows using the same character as the delimiter.
                // Example. 
                // fld1, fld2, descricao
                // field1, field2, each field\, one column
                if (line.contains("\\")) {
                    line = line.replace("\\" + delimiter, "$&$&$&");
                }
                String[] values = line.split(delimiter, -1);

                if (line.contains("$&$&$&")) {
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].replace("$&$&$&", delimiter);
                    }
                }
                rows.add(values);
            }
            return rows;
        } catch (IOException ex) {
            LOG.error("IO Exception", ex);

        }
        return null;

    }
}
