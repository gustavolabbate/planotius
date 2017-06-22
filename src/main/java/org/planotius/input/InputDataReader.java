package org.planotius.input;

/**
 *
 * @author ggodoy
 */
@FunctionalInterface
public interface InputDataReader {

    InputData read(String file);
}
