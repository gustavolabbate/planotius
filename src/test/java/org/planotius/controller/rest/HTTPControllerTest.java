package org.planotius.controller.rest;

import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.planotius.controller.http.HTTPController;

/**
 *
 * @author ggodoy
 */
public class HTTPControllerTest {

    private HTTPController httpController;
    private static final Logger LOG = Logger.getLogger(HTTPControllerTest.class.getName());

    @Test
    public void verifyIDfromGitHubUser() throws IOException  {
        String name = "gustavolabbate";
        String rest = "/users/";
        httpController = new HTTPController("https://api.github.com" + rest + name);

        GitHubUser user = httpController
                .retrieveResourceFromResponse(
                        GitHubUser.class,
                        httpController.retrieveJsonAsString()
                );

        LOG.info(user.getLogin() + " - " + user.getHtml_url());

        assertEquals(user.getId(), "2988441");
    }

    @Test
    public void getCurrency() throws IOException {
        String rest = "/latest";
        httpController = new HTTPController("http://api.fixer.io" + rest);

        assertEquals(HttpStatus.SC_OK, httpController.getHttpResponse().getStatusLine().getStatusCode());

        Currency currency = new Currency();
        currency.setJsonObject(httpController.retrieveJsonObject());
        LOG.info("Real: " + currency.getBRL());
        LOG.info("Dolar: " + currency.getUSD());
        assertNotNull(currency.getBRL());
        assertNotNull(currency.getUSD());

    }

}
