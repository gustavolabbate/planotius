/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author ggodoy
 */
public class HTTPHelper {

    private HttpResponse httpResponse;

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public <T> T retrieveResourceFromResponse(Class<T> clazz)
            throws IOException {
        String jsonFromResponse = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper mapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }

    public <T> T retrieveResourceFromResponse(Class<T> clazz, String JSONString)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(JSONString, clazz);
    }

    public JSONObject retrieveJsonObject() throws IOException {
        return new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
    }

    public JSONObject retrieveJsonObject(String JSONString) throws IOException {
        return new JSONObject(JSONString);
    }

    public String retrieveJsonAsString() throws IOException {
        return EntityUtils.toString(httpResponse.getEntity());
    }
}
