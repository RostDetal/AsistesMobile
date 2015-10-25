package com.slk.asistes.Managers;

import android.content.Context;
import android.content.res.AssetManager;

import com.slk.asistes.Data.Product;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by ViS on 20.10.15.
 */
public class DataManager extends BaseManager {

    private Hashtable<String, Object> _liveData;
    private ArrayList<Product> _products;

    public boolean IsDatabaseCreationInProgress = false;


    public DataManager(){
        Init();
    }

    public void Init()
    {
        super.Init(this.toString());
        _liveData = new Hashtable<String, Object>();
        _products = new ArrayList<Product>();
    }

    public void SetLiveData(String key, Object data)
    {
        Object keepedData = GetLiveData(key);
        if(keepedData==null)
            _liveData.put(key, data);
        else{
            if(!keepedData.equals(data)) {
                ClearLiveData(key);
                _liveData.put(key, data);
            }
        }
    }

    public Object GetLiveData(String key)
    {
        return _liveData.containsKey(key) ? _liveData.get(key) : null;
    }

    public void ClearLiveData(String key)
    {
        if(_liveData.containsKey(key)){
            _liveData.remove(key);
        }
    }

    public ArrayList<Product> LiveProducts()
    {
        return _products;
    }

    public Product GetLiveProductByID(int _id)
    {
        for (int i=0; i<_products.size(); i++)
        {
            Product product =_products.get(i);
            if(product.Id == _id)
                return product;
        }

        return null;
    }

    public void ParseProducts(String data)
    {
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray productsArray = obj.getJSONArray(Utils.DATAMANAGER_PRODUCTS_KEY);

            for (int i = 0; i < productsArray.length(); i++) {
                try {
                    _products.add(new Product(productsArray.getJSONObject(i)));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }

    public StringBuilder GetTextFileContentFromAssets(Context context, String file_name)
    {
        StringBuilder total = null;
        AssetManager am = context.getAssets();
        try {
            InputStream brandsStream = am.open(file_name);
            BufferedReader r = new BufferedReader(new InputStreamReader(brandsStream));
            total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

        }catch(IOException ex)
        {
            ex.printStackTrace();
        }

        return total;
    }



}
