package com.example.digikala.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.IOnBackPress;
import com.example.digikala.R;
import com.example.digikala.adapter.ProductAdapter;
import com.example.digikala.adapter.ProductCategoryAdapter;
import com.example.digikala.model.product.CategoriesItem;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.Repository;

import java.util.List;


public class SearchFragment extends Fragment implements IOnBackPress {

    public static final String ARGS_QUERY = "ARGS_QUERY";

    private String mQuery = "";

    private RecyclerView mRecyclerView;
    private ProductCategoryAdapter mAdapter;
    private Repository mRepository;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String query) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository(getContext());
        mQuery = (String) getArguments().get(ARGS_QUERY);

        mRepository.fetchSearchProducts(mQuery, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapter == null) {
                    mAdapter = new ProductCategoryAdapter(getContext(), items);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.setProductsItem(items);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.search_recycler_view);
    }


    @Override
    public boolean onBackPressed() {
        return true;
    }
}