package com.slk.asistes.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slk.asistes.Activities.ResultsDataActivity;
import com.slk.asistes.Data.Product;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Utils;

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

    // Provide a reference to the views for each data card_product_item
    // Complex data items may need more than one view per card_product_item, and
    // you provide access to all the views for a data card_product_item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data card_product_item is just a string in this case
        public TextView productLabel;
        public TextView priceLabel;
        public TextView artikulLabel;
        public TextView countLabel;
        public TextView sellerLabel;
        public TextView locationLabel;
        public TextView availableLabel;
        public ImageView productImage;
        public CardView currentCardView;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            currentCardView = (CardView) itemView.findViewById(R.id.product_card_item);
            productLabel = (TextView)itemView.findViewById(R.id.card_label);
            productImage = (ImageView)itemView.findViewById(R.id.card_image);
            priceLabel = (TextView)itemView.findViewById(R.id.card_price);
            countLabel = (TextView)itemView.findViewById(R.id.card_count);
            artikulLabel = (TextView)itemView.findViewById(R.id.card_sku);
            sellerLabel = (TextView)itemView.findViewById(R.id.card_seller);
            locationLabel = (TextView)itemView.findViewById(R.id.card_location);
            availableLabel = (TextView)itemView.findViewById(R.id.card_available);
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
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_item, parent, false);
        ViewHolder vh = new ViewHolder((CardView)view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String name = mDataset.get(position).Name();
        int price = mDataset.get(position).Price();
        int count = mDataset.get(position).TotalOnHand();
        String sku = mDataset.get(position).Sku();
        String location = mDataset.get(position).Location();
        String available = mDataset.get(position).AvailableOn();
        String previewPath = mDataset.get(position).PreviewUrl();

        Context context =  ApplicationContext.Instance().getAndroidContext();

        Glide.with(ApplicationContext.Instance().getAndroidContext())
                .load(previewPath)
                .asBitmap()
                .centerCrop()
                .into(holder.productImage);

        holder.productLabel.setText(name);
        holder.priceLabel.setText(price+" "+ Utils.RUBLE);
        holder.countLabel.setText(context.getString(R.string.product_count)+" "+count );
        holder.artikulLabel.setText(context.getString(R.string.product_artikul)+ " " +sku);
        holder.locationLabel.setText(location);
//        holder.sellerLabel.setText(context.getString(R.string.product_seller) + " " + sku);
        holder.availableLabel.setText(context.getString(R.string.product_available) + " " + available);

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