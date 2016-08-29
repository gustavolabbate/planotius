package org.planotius.controller.rest;

import java.io.IOException;
import org.apache.http.HttpStatus;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.planotius.controller.HTTPController;

/**
 *
 * @author ggodoy
 */
public class HTTPControllerTest {

    private HTTPController httpController;

    @Test
    public void verifyIDfromGitHubUser() throws IOException, Exception {
        String name = "gustavolabbate";
        String rest = "/users/";
        httpController = new HTTPController("https://api.github.com" + rest + name);

        GitHubUser user = httpController
                .retrieveResourceFromResponse(
                        GitHubUser.class,
                        httpController.retrieveJsonAsString()
                );

//        System.out.println(restController.retrieveJsonAsString());
        System.out.println(user.getLogin() + " - " + user.getHtml_url());

        assertEquals(user.getId(), "2988441");
    }

    @Test
    public void getCurrency() throws IOException, Exception {
        String rest = "/latest";
        httpController = new HTTPController("http://api.fixer.io" + rest);
//        httpController.retrieveJsonAsString();
        
        assertEquals(HttpStatus.SC_OK, httpController.getHttpResponse().getStatusLine().getStatusCode());

        Currency currency = new Currency();
        currency.setJsonObject(httpController.retrieveJsonObject());
        System.out.println("Real: " + currency.getBRL());
        System.out.println("Dolar: " + currency.getUSD());
        assertNotNull(currency.getBRL());
        assertNotNull(currency.getUSD());

    }

}
