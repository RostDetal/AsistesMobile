package com.slk.asistes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.slk.asistes.Adapters.ProductsAdapter;
import com.slk.asistes.R;
import com.slk.asistes.Static.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultsDataActivity extends AppCompatActivity {

    public interface ProductItemClickProcess {
        public void TryProcess(JSONObject result);
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_data);


        Intent activityIntent = getIntent();
        String json = activityIntent.getStringExtra("products");

        JSONArray productsArray = null;
        try{
            JSONObject obj = new JSONObject(json);
            productsArray = obj.getJSONArray("products");
            Logger.toConsole("Received");

        }catch(JSONException e)
        {
            e.printStackTrace();
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.scroll_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ProductsAdapter(productsArray, new ProductItemClickProcess() {
            @Override
            public void TryProcess(JSONObject result) {
                Intent productCardIntent = new Intent(ResultsDataActivity.this, ProductCardActivity.class);
                productCardIntent.putExtra("product", result.toString());
                startActivity(productCardIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}



