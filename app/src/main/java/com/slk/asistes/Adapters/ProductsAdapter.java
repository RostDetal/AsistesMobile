package com.slk.asistes.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.slk.asistes.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by VIS on 18.10.2015.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private JSONArray mDataset;

    // Provide a reference to the views for each data product_item
    // Complex data items may need more than one view per product_item, and
    // you provide access to all the views for a data product_item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data product_item is just a string in this case
        public TextView productLabel;
        public ImageView productImage;
        public ViewHolder(View itemView) {
            super(itemView);
            productLabel = (TextView)itemView.findViewById(R.id.card_label);
            productImage = (ImageView)itemView.findViewById(R.id.card_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductsAdapter(JSONArray myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
       // ...
        ViewHolder vh = new ViewHolder((LinearLayout)v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String name = "";
        String image = "";
        try {
            name = (String)mDataset.getJSONObject(position).get("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.productLabel.setText(name);


    //    holder.productImage.setImageURI("http://stage.asistes.com");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length();
    }
}