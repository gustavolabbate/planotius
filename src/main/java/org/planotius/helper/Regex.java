package org.planotius.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pandrade1
 */
public class Regex {

    /*
     * return a string from value using regular expression
     * @param value
     * @param pattern
     * @param group
     */
    public String getGroupByIndex(String value, String pattern, int group) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        if(m.find()) {
            value = m.group(group);
            value = value.replaceAll("\\(", "").replaceAll("\\)","");
        }
        return value;
    }

}
