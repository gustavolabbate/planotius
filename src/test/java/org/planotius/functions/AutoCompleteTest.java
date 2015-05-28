package org.planotius.functions;

import org.planotius.controller.Controller;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.planotius.pageobjects.KayakPage;

/**
 *
 * @author ggodoy
 */
public class AutoCompleteTest {

    static KayakPage kayak;

    @BeforeClass
    public static void setup() {
        kayak = new KayakPage().init(KayakPage.class);
        kayak.setUrl("http://www.kayak.com.br/");
        kayak.openUrl();
    }

    @Test
    public void shouldGetAutoCompleteSugestions() {
//        assertEquals("Jackson", kayak.getCellValueFromTableWithHeader("1", "Last Name"));
        kayak.isValueInHotelFieldAutocomplete("campi", "Campinas, Brasil");

    }

    @AfterClass
    public static void tearDown() {
        Controller.stop();
    }

}
