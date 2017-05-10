package org.planotius.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pandrade1
 */
public class Regex {

    /**
     * get drivers count on storage's inventory
     * @param value
     * @param pattern
     * @param group
     * @return int
     */
    public int getDrives(String value, String pattern, int group) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        if(m.find()) {
            value = m.group(group);
            value = value.replaceAll("\\(", "").replaceAll("\\)","");
        }
        return Integer.parseInt(value);
    }

}
