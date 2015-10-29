package com.slk.asistes.Data;

import com.slk.asistes.Static.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ViS on 21.10.15.
 */
public class Product {
    private int Id;
    private String productName;
    private String description;
    private String slug;
    private int price;
    private int totalOnHand;
    private String availableOn;
    private ArrayList<String> images = new ArrayList<String>();
    private String sku;

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

            try
            {
                ParseDate(availableOn);
            }catch(ParseException pe)
            {
                pe.printStackTrace();
            }

        }catch(JSONException ex){
            ex.printStackTrace();
        }

    }

    private Date ParseDate(String data) throws ParseException {
        //2015-10-04T00:00:00.000Z
        String[] tempSplitted = data.split("[T]");
        String stringDate = tempSplitted[0];

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = format.parse(stringDate);

        return date;
    }

    public final int ID(){ return Id; }
    public final String Name(){ return productName; }
    public final String Description(){ return description == null ? "" : description; }
    public final String Slug(){ return slug == null ? "" : slug; }
    public final int Price(){ return price; }
    public final int TotalOnHand(){ return totalOnHand; }
    public final String AvailableOn(){ return availableOn == null ? "" : availableOn; }
    public final ArrayList<String> Images(){ return images; }
    public final String Sku(){ return sku == null ? "" : sku; }
}
