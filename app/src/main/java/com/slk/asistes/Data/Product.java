package com.slk.asistes.Data;

import com.slk.asistes.Static.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ViS on 21.10.15.
 */
public class Product {
    public int Id;
    public String productName;
    public String description;
    public String slug;
    public int price;
    public int totalOnHand;
    public String availableOn;
    public ArrayList<String> images = new ArrayList<String>();
    public String sku;

    public Product(JSONObject data)
    {
        try{

            Id = data.getInt("id");
            productName = data.getString("name");
            description = data.getString("description");
            slug = data.getString("slug");
            price = data.getInt("price");
            images.add("http://stage.asistes.com/system/products/5/large/1-s.png?1444804983");
            totalOnHand = data.getInt("total_on_hand");
            availableOn = data.getString("available_on");

        }catch(JSONException ex){
            ex.printStackTrace();
        }

    }
}
