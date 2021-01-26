package com.example.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryHolder> {

    private Context mContext;
    private List<ProductsItem> mProductsItem;

    public List<ProductsItem> getProductsItem() {
        return mProductsItem;
    }

    public void setProductsItem(List<ProductsItem> productsItem) {
        mProductsItem = productsItem;
        notifyDataSetChanged();
    }

    public ProductCategoryAdapter(Context context, List<ProductsItem> productsItem) {
        mContext = context;
        mProductsItem = productsItem;
    }

    @NonNull
    @Override
    public ProductCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_category_holder, parent, false);

        return new ProductCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCategoryHolder holder, int position) {
        ProductsItem productItem = mProductsItem.get(position);
        holder.bindProduct(productItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, ProductDetailFragment.newInstance(productItem))
                                .commit();

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductsItem.size();
    }


    public class ProductCategoryHolder extends RecyclerView.ViewHolder {

        private TextView mName, mPrice;
        private ImageView mImage;
        private View mItemView;


        public ProductCategoryHolder(@NonNull View itemView) {
            super(itemView);
            findHolderViews(itemView);

        }


        private void findHolderViews(@NonNull View itemView) {
            mName = itemView.findViewById(R.id.product_category_holder_name);
            mPrice = itemView.findViewById(R.id.product_category_holder_price);
            mImage = itemView.findViewById(R.id.product_category_holder_image);

            mItemView = itemView;

        }

        private void bindProduct(ProductsItem productItem) {
            mName.setText(productItem.getName() + "");
            mPrice.setText(productItem.getPrice() + " " + mContext.getResources().getString(R.string.toman));
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
