/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.planotius.controller.rest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ggodoy
 */
public class Currency {

    private JSONObject jsonObject;
    private String BRL;
    private String USD;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject JsonObject) {
        this.jsonObject = JsonObject;
    }

    public String getBRL() {
        return Double.toString(jsonObject.getJSONObject("rates").getDouble("BRL"));
    }

    public void setBRL(String BRL) {
        this.BRL = BRL;
    }

    public String getUSD() {
        return Double.toString(jsonObject.getJSONObject("rates").getDouble("USD"));
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

}
