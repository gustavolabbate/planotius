package org.planotius.controller.http;

import org.apache.log4j.Logger;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author gustavolabbate
 */
public class HTTPController extends HTTPHelper {

    private static final Logger LOG = Logger.getLogger(HTTPController.class.getName());

    private String httpUrl;
    private HttpUriRequest request;

    public HTTPController(String httpUrl) throws IOException {
        this.httpUrl = httpUrl;
        setHttpResponse(getResponseFromRequest());
    }

    public HTTPController(String httpUrl, String method, StringEntity params) throws IOException {
        this.httpUrl = httpUrl;
        setHttpResponse(getResponsefromPOST(params));

    }

    private HttpResponse getResponsefromPOST(StringEntity params) throws IOException {
        HttpPost newRequest = new HttpPost(httpUrl);
        newRequest.setEntity(params);
        setRequest(newRequest);
        return HttpClientBuilder.create().build().execute(newRequest);
    }

    private HttpResponse getResponseFromRequest() throws IOException {
        setRequest(new HttpGet(httpUrl));
        return HttpClientBuilder.create().build().execute(request);
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public HttpUriRequest getRequest() {
        return request;
    }

    public void setRequest(HttpUriRequest request) {
        this.request = request;
    }

}
