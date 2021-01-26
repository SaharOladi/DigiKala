package com.example.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.fragment.ProductDetailFragment;
import com.example.digikala.model.product.ImagesItem;
import com.example.digikala.model.product.ProductsItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecyclerHolder> implements Filterable {

    private Context mContext;
    private List<ProductsItem> mProductsItem;
    private List<ProductsItem> mSearchProductsItem;

    public List<ProductsItem> getProductsItem() {
        return mProductsItem;
    }

    public void setProductsItem(List<ProductsItem> productsItem) {
        mProductsItem = productsItem;
        if (productsItem != null)
            this.mSearchProductsItem = new ArrayList<>(productsItem);
        notifyDataSetChanged();
    }

    public ProductAdapter(Context context, List<ProductsItem> productsItem) {
        mContext = context;
        mProductsItem = productsItem;
        mSearchProductsItem = new ArrayList<>(productsItem);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_view_holder, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        ProductsItem productItem = mProductsItem.get(position);
        holder.bindProduct(productItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ProductDetailFragment.newInstance(productItem))
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductsItem.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<ProductsItem> filteredList = new ArrayList<>();

                if (charSequence.toString().isEmpty()) {
                    filteredList.addAll(mSearchProductsItem);
                } else {
                    for (ProductsItem productsItem : mSearchProductsItem) {
                        if (productsItem.getName().toLowerCase().trim().contains(charSequence.toString().toLowerCase().trim())) {
                            filteredList.add(productsItem);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (mProductsItem != null)
                    mProductsItem.clear();
                if (filterResults.values != null)
                    mProductsItem.addAll((Collection<? extends ProductsItem>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView mName, mPrice;
        private ImageView mImage;
        private View mItemView;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            findHolderViews(itemView);

        }


        private void findHolderViews(@NonNull View itemView) {
            mName = itemView.findViewById(R.id.product_view_holder_title);
            mPrice = itemView.findViewById(R.id.product_view_holder_price);
            mImage = itemView.findViewById(R.id.product_view_holder_image_cover);

            mItemView = itemView;

        }

        private void bindProduct(ProductsItem productItem) {
            mName.setText(productItem.getName() + "");
            mPrice.setText(productItem.getPrice() + "");
            List<ImagesItem> imagesItems = productItem.getImages();
            List<String> imagesItemList = new ArrayList<>();
            for (int i = 0; i < imagesItems.size(); i++) {
                imagesItemList.add(imagesItems.get(i).getSrc());
            }

            for (int i = 0; i < imagesItemList.size(); i++) {
                if (imagesItemList.get(i) == null)
                    Glide.with(mItemView)
                            .load(R.drawable.ic_placeholder_recycler)
                            .placeholder(R.drawable.ic_placeholder_recycler)
                            .fitCenter()
                            .into(mImage);

                else
                    Glide.with(mItemView)
                            .load(imagesItemList.get(i))
                            .placeholder(R.drawable.ic_placeholder_recycler)
                            .fitCenter()
                            .into(mImage);
            }


        }

    }

}
