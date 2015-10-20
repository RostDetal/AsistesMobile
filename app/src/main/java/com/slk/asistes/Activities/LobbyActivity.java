package com.slk.asistes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.slk.asistes.R;
import com.slk.asistes.Static.Logger;
import com.slk.asistes.Tasks.SearchProductsTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LobbyActivity extends AppCompatActivity {

    //define callback interface
    public interface ProductLoadedCallback {
       public void onProductsLoadingDone(String result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void OnButtonSearchClick(View view)
    {
//        Toast.makeText(this, "Fuck you, Spillberg!!", Toast.LENGTH_SHORT).show();
//        EditText label = (EditText) findViewById(R.id.search_field);
//
//        SearchProductsTask task = new SearchProductsTask(new ProductLoadedCallback() {
//           @Override
//           public void onProductsLoadingDone(String result) {
//               Intent intent = new Intent(LobbyActivity.this, ResultsDataActivity.class);
//               startActivity(intent);
//           }
//        });
//        task.ExecuteWithData(label.getText().toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lobby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar product_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
