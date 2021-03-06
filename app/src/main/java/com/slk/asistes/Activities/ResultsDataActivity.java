package com.slk.asistes.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.slk.asistes.Adapters.ProductsAdapter;
import com.slk.asistes.Data.Product;
import com.slk.asistes.Fragments.ProductCardFragment;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;
import com.slk.asistes.Static.Utils;
import com.slk.asistes.Tasks.SearchProductsTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsDataActivity extends AppCompatActivity implements ProductCardFragment.OnFragmentInteractionListener {

    @Bind(R.id.loader_progress_bar)
    CircularProgressView progressBar;

    @Bind(R.id.search_results_count)
    TextView searchResultsCountLabel;

    public interface ProductLoadedCallback {
        public void onProductsLoadingDone(String result);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Logger.toConsole("Blia");
    }

    public interface ProductItemClickProcess {
        public void TryProcess(Product _product);
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SearchProductsTask searchTask;

    private boolean isSuspended = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_data);
        ButterKnife.bind(this);




        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String search_text = intent.getStringExtra(Utils.INTENT_SEARCH_QUERY_EXTRA_ID);

        mRecyclerView = (RecyclerView) findViewById(R.id.scroll_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().hide();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!HasCachedResultProducts())
        {
            searchTask = new SearchProductsTask(new ProductLoadedCallback() {
                @Override
                public void onProductsLoadingDone(String result) {
                    InitResultProductsList();
                }
            });
            searchTask.ExecuteWithData(search_text);
        }else{
            InitResultProductsList();
        }
    }

    private void InitResultProductsList()
    {

        searchResultsCountLabel.setText(ApplicationContext.Instance().getAndroidContext().getString(R.string.products_results)+" "+ApplicationContext.Instance().DataManager().LiveProducts().size());
        mAdapter = new ProductsAdapter(ApplicationContext.Instance().DataManager().LiveProducts(), new ProductItemClickProcess() {
            @Override
            public void TryProcess(Product _product) {

                isSuspended = true;
                Intent productCardIntent = new Intent(ResultsDataActivity.this, ProductCardActivity.class);
                productCardIntent.putExtra(Utils.INTENT_PRODUCT_CARD_EXTRA_ID, _product.ID());
                startActivity(productCardIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        getSupportActionBar().show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        isSuspended = false;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(super.isFinishing() && !isSuspended) {
            ApplicationContext.Instance().DataManager().LiveProducts().clear();
            if(searchTask!=null && !searchTask.isCancelled()) {
                searchTask.cancel(true);
            }
            Logger.toConsole("All data cleared");
        }

        Logger.toConsole("Destroy");
    }


    private boolean HasCachedResultProducts()
    {
        return ApplicationContext.Instance().DataManager().LiveProducts().size() > 0;
    }

}



