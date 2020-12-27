package com.example.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikala.R;
import com.example.digikala.model.CategoriesItem;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder> {

    private static final String TAG = "CategoryAdapter";
    private Context mContext;
    private List<CategoriesItem> mCategoriesItems;
    private ProductAdapter mProductAdapter;


    public List<CategoriesItem> getCategoriesItem() {
        return mCategoriesItems;

    }

    public void setCategoriesItem(List<CategoriesItem> categoriesItems) {
        mCategoriesItems = categoriesItems;
        notifyDataSetChanged();
    }

    public CategoryListAdapter(Context context, List<CategoriesItem> categoriesItems) {
        mContext = context;
        mCategoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.category_view_holder, parent, false);

        return new CategoryListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListHolder holder, int position) {
        CategoriesItem categoriesItem = mCategoriesItems.get(position);

        holder.bindCategory(categoriesItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, CategoryListFragment.newInstance(categoriesItem.getId()))
//                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoriesItems.size();
    }


    public class CategoryListHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryName, mCategorySeeMore;
        private RecyclerView mCategoryRecyclerView;
        private View mItemView;


        public CategoryListHolder(@NonNull View itemView) {
            super(itemView);
            findHolderViews(itemView);
        }


        private void findHolderViews(@NonNull View itemView) {
            mCategoryName = itemView.findViewById(R.id.category_list_name);
            mCategorySeeMore = itemView.findViewById(R.id.category_list_see_more);
            mCategoryRecyclerView = itemView.findViewById(R.id.category_list_recyclerview);

            mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false));

            mItemView = itemView;

        }

        private void bindCategory(CategoriesItem categoriesItem) {
            mCategoryName.setText(categoriesItem.getName() + "");
            if (mProductAdapter == null) {
                mProductAdapter = new ProductAdapter(mContext, null);
                mCategoryRecyclerView.setAdapter(mProductAdapter);
            } else {
                mProductAdapter.setProductsItem(null);
                mProductAdapter.notifyDataSetChanged();
            }
        }

    }

    public interface OnCategoryClickListener {
        void onCategoryClick(int id);
    }

}

