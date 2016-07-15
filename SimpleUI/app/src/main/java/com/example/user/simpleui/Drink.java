package com.example.user.simpleui;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by user on 2016/7/14.
 */

/**
 * Created by user on 2016/7/14.
 */
public class Drink {
    String name;
    int mPrice = 0;
    int lPrice = 0;
    int imageId;

    public JSONObject getJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("price", mPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static Drink newInstanceWithData(String Data)
    {
        Drink drink = new Drink();
        try {
            JSONObject jsonObject = new JSONObject(Data);
            drink.name = jsonObject.getString("name");
            drink.lPrice = jsonObject.getInt("lPrice");
            drink.mPrice = jsonObject.getInt("mPrice");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return drink;
    }
}