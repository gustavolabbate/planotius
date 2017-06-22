package org.planotius.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pandrade1
 */
public class Regex {

    /*
     * Return a string from value using regular expression. 
     * When the pattern and String don´t match, the result message is:
     * "Value didn´t match!"
     * So be wise to validate the correct return! :)
     * @param value
     * @param pattern
     * @param group
     */
    public String getGroupByIndex(String value, String pattern, int group) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        if (m.find()) {
            return m.group(group);
        }
        return "Value didn´t match!";
    }

    /*
     * return a string from value using regular expression, without group Index
     * When the pattern and String don´t match, the result message is:
     * "Value didn´t match!"
     * So be wise to validate the correct return! :)
     * @param value
     * @param pattern
     * @param group
     */
    public String getMatch(String value, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        if (m.find()) {
            return m.group();
        }
        return "Value didn´t match!";
    }

}
