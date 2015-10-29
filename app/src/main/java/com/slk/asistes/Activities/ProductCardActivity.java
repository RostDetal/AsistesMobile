package com.slk.asistes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.slk.asistes.AsistesApplication;
import com.slk.asistes.Data.Product;
import com.slk.asistes.Fragments.SearchTabContentFragment;

import com.slk.asistes.Managers.DataManager;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductCardActivity extends AppCompatActivity {

    @Bind(R.id.product_card_imageview)
    ImageView image;

    @Bind(R.id.product_card_name)
    TextView name;

    @Bind(R.id.product_card_price)
    TextView price;

    @Bind(R.id.product_card_description)
    TextView description;

    @Bind(R.id.product_card_brand)
    TextView brandValue;

    @Bind(R.id.product_card_model)
    TextView modelValue;

    @Bind(R.id.product_card_modification)
    TextView modificationValue;

    @Bind(R.id.product_card_year)
    TextView yearValue;

    private Product viewableProduct;

    private final DataManager dataManager = ApplicationContext.Instance().DataManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra(Utils.INTENT_PRODUCT_CARD_EXTRA_ID, 0);
        viewableProduct = ApplicationContext.Instance().DataManager().GetLiveProductByID(id);
        InitProductCard();
    }

    public void OnRequestButtonClick(View v)
    {
        AsistesApplication.tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Products")
                .setAction("Request")
                .setLabel(name.getText().toString())
                .build());
    }


    private void InitProductCard()
    {
        Glide.with(this).load(viewableProduct.Images().get(0)).into(image);

        brandValue.setText(GetValue(SearchTabContentFragment.DataType.Brand));
        modelValue.setText(GetValue(SearchTabContentFragment.DataType.Model));
        modificationValue.setText(GetValue(SearchTabContentFragment.DataType.Modification));
        yearValue.setText(GetValue(SearchTabContentFragment.DataType.Year));

        name.setText(viewableProduct.Name());
        price.setText(viewableProduct.Price() + " " + Utils.RUBLE);

        description.setText(viewableProduct.Description());
    }

    private String GetValue(String type)
    {
        HashMap<Integer, ArrayList<Object>> globalData =   (HashMap<Integer, ArrayList<Object>>)dataManager.GetLiveData(type);
        ArrayList<Object> data = (ArrayList<Object>) globalData.values().toArray()[0];
        return (String)data.get(1);
    }

}
