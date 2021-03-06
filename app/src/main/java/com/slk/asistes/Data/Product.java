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
    private String type;
    private int disassemblyId;
    private String previewUrl;
    private String currencyType;
    private String location;

    public Product(JSONObject data)
    {
        try{
            Id = data.getInt("id");
            productName = data.getString("name");
            description = data.getString("description");
            //slug = data.getString("slug");
            price = data.getInt("cost");
            disassemblyId = data.getInt("disassembly_id");
            images.add("http://stage.asistes.com/system/products/5/large/1-s.png?1444804983");
            totalOnHand = data.getInt("total_on_hand");
            previewUrl = data.getString("preview").length()>0 ? data.getString("preview") : "http://stage.asistes.com/assets/noimage/mini-7c32975a7a438d2e002f8b0964e25ad0.png";
            sku = data.getString("sku");
            currencyType =  data.getString("currency");
            location = data.getString("location");


            String tempDate = data.getString("available_on");

            try
            {
                availableOn = ParseDate(tempDate);
            }catch(ParseException pe)
            {
                pe.printStackTrace();
            }

        }catch(JSONException ex){
            ex.printStackTrace();
        }

    }

    private String ParseDate(String data) throws ParseException {
        String[] tempSplitted = data.split("[T]");
        String stringDate = tempSplitted[0];

        DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = fromFormat.parse(stringDate);

        String toFormat = "dd MMMM yyyy";
        DateFormat toformatter = new SimpleDateFormat(toFormat,Locale.getDefault());
        String result = toformatter.format(date);

        return result;
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
    public final String PreviewUrl(){ return previewUrl; }
    public final String CurrencyType(){ return currencyType; }
    public final String Location(){ return location; }
}
