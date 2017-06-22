package org.planotius.controller.rest;

import org.json.JSONObject;

/**
 *
 * @author ggodoy
 */
public class Currency {

    private JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject JsonObject) {
        this.jsonObject = JsonObject;
    }

    public String getBRL() {
        return Double.toString(jsonObject.getJSONObject("rates").getDouble("BRL"));
    }

    public String getUSD() {
        return Double.toString(jsonObject.getJSONObject("rates").getDouble("USD"));
    }

}
