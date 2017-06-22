package org.planotius.input.csv;

import org.planotius.input.InputDataReader;
import org.planotius.input.InputData;
import org.planotius.helper.CSVReader;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author ggodoy
 */
public class CSVInputDataReader implements InputDataReader {

    private static final Logger log = Logger.getLogger(CSVInputDataReader.class.getName());

    @Override
    public InputData read(String file) {
        CSVReader csv = new CSVReader();
        InputData inputData = new InputData();

        //FileExists?
        String filename = file;
        String testResourcespath = "src/test/resources/";
        String mainResourcespath = "src/main/resources/";

        if (!(new File(filename).exists())) {
            if (new File(testResourcespath + file).exists()) {
                filename = testResourcespath + file;
            } else if (new File(mainResourcespath + file).exists()) {
                filename = mainResourcespath + file;
            }
        }

        inputData.InputData = csv.getList(filename);
        log.info("CSV file: " + file);
        return inputData;
    }

}
