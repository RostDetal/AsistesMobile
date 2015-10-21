package com.slk.asistes.Tasks;

import android.os.AsyncTask;
import com.slk.asistes.Activities.LobbyPagerActivity;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by VIS on 18.10.2015.
 */
public class SearchProductsTask extends AsyncTask <String, Void, String>
{

    final LobbyPagerActivity.ProductLoadedCallback callback;

    private String searchData;
    private String token = "48b33ee3a3701c48fb35f75b79be4aedb1035b4b7c84d898";


    public SearchProductsTask(LobbyPagerActivity.ProductLoadedCallback callback) {
        this.callback = callback;
    }


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
            ApplicationContext.Instance().DataManager().SetLiveData("products", content);
        }
        catch (IOException ex){
            content = ex.getMessage();
        }

        return content;
    }

    @Override
    protected void onPostExecute(String content) {

        try
        {

            callback.onProductsLoadingDone(content);
            Logger.toConsole(content);

        }catch(Exception ex){
            Logger.toConsole(ex.getMessage());
        }

    }


}
