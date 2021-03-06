package com.example.digikala.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.digikala.fragment.CategoryListFragment;
import com.example.digikala.fragment.ShoppingFragment;
import com.example.digikala.model.product.ImagesItem;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.ShoppingRepository;
import com.example.digikala.utils.ShoppingPreferences;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.RecyclerHolder> {

    public static final String TAG = "CardAdapter";
    private Context mContext;
    private List<ProductsItem> mProductsItem;

    private int mFinalPriceValue = 0;

    public int getFinalPriceValue() {
        return mFinalPriceValue;
    }

    public void setFinalPriceValue(int finalPriceValue) {
        mFinalPriceValue = finalPriceValue;
    }

    public List<ProductsItem> getProductsItem() {
        return mProductsItem;
    }

    public void setProductsItem(List<ProductsItem> productsItem) {
        mProductsItem = productsItem;
        notifyDataSetChanged();
    }

    public CardAdapter(Context context, List<ProductsItem> productsItem) {
        mContext = context;
        mProductsItem = productsItem;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.card_holder_item, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        ProductsItem productItem = mProductsItem.get(position);
        holder.bindProduct(productItem);
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return mProductsItem.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView mName, mSalePrice;
        private com.google.android.material.button.MaterialButton mMaterialButtonPlus,
                mMaterialButtonMinus, mMaterialButtonDelete;
        private androidx.appcompat.widget.AppCompatTextView mCount, mFinalPrice;
        private ImageView mImageView;

        private int basePrice = 0;

        private View mItemView;

        private int mProductCount = 1;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            findHolderViews(itemView);
        }


        private void findHolderViews(@NonNull View itemView) {
            mName = itemView.findViewById(R.id.card_name);
            mMaterialButtonPlus = itemView.findViewById(R.id.card_plus_button);
            mMaterialButtonMinus = itemView.findViewById(R.id.card_minus_button);
            mMaterialButtonDelete = itemView.findViewById(R.id.card_delete);
            mCount = itemView.findViewById(R.id.card_count);
            mFinalPrice = itemView.findViewById(R.id.card_final_price);
            mImageView = itemView.findViewById(R.id.card_image_view);
            mSalePrice = itemView.findViewById(R.id.card_sale_price);
            mItemView = itemView;

        }

        private void bindProduct(ProductsItem productItem) {
            mName.setText(productItem.getName() + "");
            mFinalPrice.setText(productItem.getPrice() +
                    " " + mContext.getResources().getString(R.string.toman));
            mCount.setText(mProductCount + "");
            basePrice = Integer.parseInt(productItem.getPrice());
            mSalePrice.setText(mProductCount * Integer.parseInt(productItem.getPrice())
                    + " " + mContext.getResources().getString(R.string.toman));

            setFinalPriceValue(mFinalPriceValue + mProductCount * basePrice);

            List<ImagesItem> imagesItems = productItem.getImages();
            if (imagesItems.get(0).getSrc().length() != 0)
                Glide.with(mItemView)
                        .load(imagesItems.get(0).getSrc())
                        .fitCenter()
                        .into(mImageView);

        }

        private void setListener(int position) {
            mMaterialButtonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mProductCount <= 0)
                        mProductCount = 0;

                    mProductCount++;

                    if (mProductCount >= 0) {
                        mCount.setText(mProductCount + "");
                        mSalePrice.setText(mProductCount * basePrice +
                                " " + mContext.getResources().getString(R.string.toman));
                        setFinalPriceValue(mFinalPriceValue + mProductCount * basePrice);
                        Log.d(TAG, "mFinalPriceValue: " + mFinalPriceValue);

                    }

                }
            });

            mMaterialButtonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProductCount--;
                    if (mProductCount >= 0) {
                        mCount.setText(mProductCount + "");
                        mSalePrice.setText(mProductCount * basePrice +
                                " " + mContext.getResources().getString(R.string.toman));
                        setFinalPriceValue(mFinalPriceValue - mProductCount * basePrice);
                        Log.d(TAG, "mFinalPriceValue: " + mFinalPriceValue);
                    }
                }
            });

            mMaterialButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShoppingRepository shoppingRepository = ShoppingRepository.getInstance(mContext);
                    shoppingRepository.deleteProduct(mProductsItem.get(position));
                    ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, ShoppingFragment.newInstance())
                            .commit();
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, mProductsItem.size());
                }
            });

        }

    }
}

