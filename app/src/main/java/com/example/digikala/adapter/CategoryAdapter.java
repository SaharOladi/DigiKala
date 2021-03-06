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
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.RequestOptions;
import com.example.digikala.R;
import com.example.digikala.fragment.CategoryListFragment;
import com.example.digikala.model.product.CategoriesItem;
import com.example.digikala.model.product.ImagesItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private static final String TAG = "CategoryAdapter";
    private Context mContext;
    private List<CategoriesItem> mCategoriesItems;

    public List<CategoriesItem> getCategoriesItem() {
        return mCategoriesItems;

    }

    public void setCategoriesItem(List<CategoriesItem> categoriesItems) {
        mCategoriesItems = categoriesItems;
        notifyDataSetChanged();
    }

    public CategoryAdapter(Context context, List<CategoriesItem> categoriesItems) {
        mContext = context;
        mCategoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.category_view_holder, parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoriesItem categoriesItem = mCategoriesItems.get(position);
        holder.bindCategory(categoriesItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, CategoryListFragment.newInstance(categoriesItem.getId()))
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoriesItems.size();
    }


    public class CategoryHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryName;
        private ImageView mCategoryImage;
        private View mItemView;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            findHolderViews(itemView);
        }


        private void findHolderViews(@NonNull View itemView) {
            mCategoryName = itemView.findViewById(R.id.category_name);
            mCategoryImage = itemView.findViewById(R.id.category_image);

            mItemView = itemView;

        }

        private void bindCategory(CategoriesItem categoriesItem) {
            mCategoryName.setText(categoriesItem.getName() + "");
            ImagesItem imageItem = categoriesItem.getImages();
            List<String> imagesItemList = new ArrayList<>();

            for (int i = 0; i < imageItem.getSrc().length(); i++) {
                imagesItemList.add(imageItem.getSrc());
            }

            for (int i = 0; i < imagesItemList.size(); i++) {
                if (imagesItemList.get(i) == null)
                    Glide.with(mItemView)
                            .load(R.drawable.ic_placeholder_recycler)
                            .placeholder(R.drawable.ic_placeholder_recycler)
                            .fitCenter()
                            .into(mCategoryImage);

                else
                    Glide.with(mItemView)
                            .load(imageItem.getSrc())
                            .placeholder(R.drawable.ic_placeholder_recycler)
                            .fitCenter()
                            .into(mCategoryImage);
            }

        }

    }

}

