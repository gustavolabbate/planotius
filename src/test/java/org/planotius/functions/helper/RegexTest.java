package org.planotius.functions.helper;

import static org.junit.Assert.*;
import org.junit.Test;
import org.planotius.helper.Regex;

/**
 *
 * @author ggodoy
 */
public class RegexTest {

    @Test
    public void shouldMatch() {
        Regex regex = new Regex();

        assertEquals("123",
                regex.getGroupByIndex("AbcDef123GH", "[0-9]+", 0));
    }
    
    @Test
    public void shouldNotMatchByIndex() {
        Regex regex = new Regex();

        assertEquals("Value didn´t match!",
                regex.getGroupByIndex("AbcDef123GH", "[NO]+", 0));
    }

    @Test
    public void shouldMatchWithoutGroup() {
        Regex regex = new Regex();

        assertEquals("123",
                regex.getMatch("AbcDef123GH", "[0-9]+"));
    }

    @Test
    public void shouldNotMatch() {
        Regex regex = new Regex();

        assertEquals("Value didn´t match!",
                regex.getMatch("AbcDef123GH", "[X]"));
        
        
    }

}
