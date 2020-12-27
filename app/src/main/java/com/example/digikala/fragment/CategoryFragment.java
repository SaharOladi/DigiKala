package com.example.digikala.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.digikala.R;
import com.example.digikala.adapter.CategoryAdapter;
import com.example.digikala.adapter.CategoryListAdapter;
import com.example.digikala.adapter.ProductAdapter;
import com.example.digikala.model.CategoriesItem;
import com.example.digikala.model.ProductsItem;
import com.example.digikala.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {

    public static final String TAG = "CategoryFragment";

    private RecyclerView mRecyclerViewCategoryOne, mRecyclerViewCategoryTwo, mRecyclerViewCategoryThree,
    mRecyclerViewCategoryFour, mRecyclerViewCategoryFive, mRecyclerViewCategorySix, mRecyclerViewCategorySeven,
    mRecyclerViewCategoryEight, mRecyclerViewCategoryNine, mRecyclerViewCategoryTen;

    private ProductAdapter mProductAdapterCategory;
    private Repository mRepository;
    private List<Integer> mCategoryIds = new ArrayList<>();

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = new Repository();

        mRepository.fetchCategory(1, new Repository.CategoryCallbacks() {
            @Override
            public void onItemResponse(List<CategoriesItem> items) {
                initCategoryRecyclerAdapter(initCategoryIds(items));
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mRecyclerViewCategoryOne= view.findViewById(R.id.category_recycler_one);
        mRecyclerViewCategoryTwo= view.findViewById(R.id.category_recycler_two);
        mRecyclerViewCategoryThree= view.findViewById(R.id.category_recycler_three);

    }

    private void initCategoryRecyclerAdapter(List<Integer> ids) {
        mRecyclerViewCategoryOne.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewCategoryTwo.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewCategoryThree.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateCategoryRecyclerAdapter(ids);
    }

    private void updateCategoryRecyclerAdapter(List<Integer> ids) {
        for (int i = 0; i < ids.size(); i++) {
            mRepository.fetchCategoryProduct(1, ids.get(i), new Repository.Callbacks() {
                @Override
                public void onItemResponse(List<ProductsItem> items) {
                    if (mProductAdapterCategory == null) {
                        mProductAdapterCategory = new ProductAdapter(getContext(), items);
                        mRecyclerViewCategoryOne.setAdapter(mProductAdapterCategory);
                    } else {
                        mProductAdapterCategory.setProductsItem(items);
                        mProductAdapterCategory.notifyDataSetChanged();
                    }
                }
            });
        }


    }

    private List<Integer> initCategoryIds(List<CategoriesItem> items){
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i <items.size() ; i++) {
            if(!ids.contains(items.get(i).getId())){
                ids.add(items.get(i).getId());
            }
        }
        return ids;
    }

    @Override
    public void onCategoryClick(int id) {
    }


}