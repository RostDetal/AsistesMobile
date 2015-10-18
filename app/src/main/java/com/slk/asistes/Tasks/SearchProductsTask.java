package com.slk.asistes.Tasks;

import android.content.Context;
import android.net.http.Headers;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.slk.asistes.Static.ApplicationContext;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIS on 18.10.2015.
 */
public class SearchProductsTask extends AsyncTask <String, Void, String>
{

    private String searchData;
    private String token = "48b33ee3a3701c48fb35f75b79be4aedb1035b4b7c84d898";

    public void ExecuteWithData(String search_data)
    {
        searchData = search_data;
        this.execute();
    }

    private String GetData()  throws IOException
    {
        String temp_url = "http://stage.asistes.com/api/products?token="+token+"&q[name_cont_any]="+searchData;
        BufferedReader reader=null;
        try {
            URL url=new URL(temp_url);

            HttpURLConnection c=(HttpURLConnection)url.openConnection();
            c.setRequestMethod("GET");

            c.setReadTimeout(10000);
            c.connect();
            reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder buf=new StringBuilder();
            String line=null;
            while ((line=reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    @Override
    protected String doInBackground(String... params) {

        String content;
        try{

            content = GetData();
        }
        catch (IOException ex){
            content = ex.getMessage();
        }

        return content;
    }

    @Override
    protected void onPostExecute(String content) {
        JSONObject convertedObject = null;

        try
        {
            convertedObject = new JSONObject(content);


        }catch(Exception ex){

        }

        Log.d("Asistes", content);
       // contentText=content;
       // contentView.setText(content);
        //Toast.makeText(getActivity(), "Данные загружены", Toast.LENGTH_SHORT)
          //      .show();
    }


}
