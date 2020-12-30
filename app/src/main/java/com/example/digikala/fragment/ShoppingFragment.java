package com.example.digikala.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.digikala.R;
import com.example.digikala.adapter.CardAdapter;
import com.example.digikala.model.ProductsItem;
import com.example.digikala.repository.Repository;
import com.example.digikala.utils.ShoppingPreferences;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    public static final String TAG = "ShoppingFragment";
    private Repository mRepository;

    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;

    private int mIndex = 0;

    private Button mFinalShop;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    public static ShoppingFragment newInstance() {
        ShoppingFragment fragment = new ShoppingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        findViews(view);
        setRecycler();
        return view;
    }


    private void setRecycler() {
        List<ProductsItem> itemList = new ArrayList<>();
        int[] itemIds = ShoppingPreferences.getPrefIntArray(getContext());
        if (itemIds != null)
            mRepository.fetchSingleProduct(itemIds[mIndex++], new Repository.SingleCallbacks() {
                @Override
                public void onItemResponse(ProductsItem item) {
                    itemList.add(item);
                    initRecyclerAdapter(mRecyclerView, mCardAdapter, itemList);
                }
            });


    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.card_recycler_view);
        mFinalShop = view.findViewById(R.id.final_shop);
    }

    private void initRecyclerAdapter(RecyclerView recyclerView,
                                     CardAdapter cardAdapter,
                                     List<ProductsItem> productItems) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRecyclerAdapter(recyclerView, cardAdapter, productItems);
    }

    private void updateRecyclerAdapter(RecyclerView recyclerView,
                                       CardAdapter cardAdapter,
                                       List<ProductsItem> productItems) {

        if (cardAdapter == null) {
            cardAdapter = new CardAdapter(getContext(), productItems);
            recyclerView.setAdapter(cardAdapter);
        } else {
            cardAdapter.setProductsItem(productItems);
            cardAdapter.notifyDataSetChanged();
        }
    }
}