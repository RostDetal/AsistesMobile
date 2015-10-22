package com.slk.asistes.Adapters;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slk.asistes.Activities.ResultsDataActivity;
import com.slk.asistes.Data.Product;
import com.slk.asistes.R;
import com.slk.asistes.Static.Logger;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by VIS on 18.10.2015.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private ArrayList<Product> mDataset;

    private ResultsDataActivity.ProductItemClickProcess parentCallback;

    private interface ItemClickListener {
        void onClick(CardView view, int position, boolean isLongClick);
    }

    // Provide a reference to the views for each data product_item
    // Complex data items may need more than one view per product_item, and
    // you provide access to all the views for a data product_item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data product_item is just a string in this case
        public TextView productLabel;
        public ImageView productImage;
        public CardView currentCardView;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            currentCardView = (CardView) itemView.findViewById(R.id.product_card_item);
            productLabel = (TextView)itemView.findViewById(R.id.card_label);
            productImage = (ImageView)itemView.findViewById(R.id.card_image);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            clickListener.onClick(currentCardView, getAdapterPosition(), false);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductsAdapter(ArrayList<Product> myDataset, ResultsDataActivity.ProductItemClickProcess clickCallbackRef) {
        mDataset = myDataset;
        parentCallback = clickCallbackRef;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
       // ...
        ViewHolder vh = new ViewHolder((LinearLayout)view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String name = (String)mDataset.get(position).productName;

        holder.productLabel.setText(name);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(CardView view, int position, boolean isLongClick) {

                parentCallback.TryProcess(mDataset.get(position));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}