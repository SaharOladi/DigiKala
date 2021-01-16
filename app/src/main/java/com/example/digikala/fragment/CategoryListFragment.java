package com.example.digikala.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.digikala.R;
import com.example.digikala.adapter.ProductCategoryAdapter;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.Repository;

import java.util.List;

public class CategoryListFragment extends Fragment {
    public static final String ARGS_ID = "ARGS_ID";
    public static final int REQUEST_CODE_ORDER = 10;
    public static final String TAG_CHOOSE_ORDER = "TAG_CHOOSE_ORDER";
    public static final String TAG = "CategoryListFragment";

    private RecyclerView mRecyclerView;
    private ProductCategoryAdapter mAdapter;
    private Repository mRepository;

    private ImageView mSort, mFilter;


    private int mCategoryId = 0;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    public static CategoryListFragment newInstance(int id) {
        CategoryListFragment fragment = new CategoryListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryId = (int) getArguments().get(ARGS_ID);
        mRepository = new Repository(getContext());

        mRepository.fetchCategoryProduct(1, mCategoryId, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(items);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);


        findViews(view);
        setListener();

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.category_product_recyclerview);
        mSort = view.findViewById(R.id.sort_order);
        mFilter = view.findViewById(R.id.filter_product);
    }


    private void setListener() {
        mSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDialogFragment orderDialogFragment = OrderDialogFragment.newInstance();
                orderDialogFragment.setTargetFragment(
                        CategoryListFragment.this, REQUEST_CODE_ORDER);
                orderDialogFragment.show(getFragmentManager(), TAG_CHOOSE_ORDER);
            }
        });

    }

    private void initRecyclerAdapter(List<ProductsItem> productsItems) {
        updateRecyclerAdapter(productsItems);
    }

    public void updateRecyclerAdapter(List<ProductsItem> productsItems) {

        if (mAdapter == null) {
        mAdapter = new ProductCategoryAdapter(getContext(), productsItems);
        mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setProductsItem(productsItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_ORDER
                && resultCode == OrderDialogFragment.RESULT_CODE_ORDER_DIALOG_FRAGMENT
                && data != null) {

            String order = (String) data.getSerializableExtra(OrderDialogFragment.EXTRA_ORDER_DIALOG_FRAGMENT);
            Log.d(TAG, "onActivityResult: " + order);
            mRepository.fetchCategoryProductByOrder(1, mCategoryId, order, new Repository.Callbacks() {
                @Override
                public void onItemResponse(List<ProductsItem> items) {
                    if (items != null)
                        initRecyclerAdapter(items);
                }
            });
        }
    }

}