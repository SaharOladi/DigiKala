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
import android.widget.TextView;

import com.example.digikala.R;
import com.example.digikala.adapter.CardAdapter;
import com.example.digikala.model.customer.Customer;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.Repository;
import com.example.digikala.repository.ShoppingRepository;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    public static final String TAG = "ShoppingFragment";
    private Repository mRepository;
    private ShoppingRepository mShoppingRepository;

    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;


    private Button mFinalShop;
    private TextView mSalePrice;

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
        mShoppingRepository = ShoppingRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        findViews(view);
        setRecycler();


        mFinalShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.sendCustomer("digikala.test@digikala.com", new Repository.CustomerCallbacks() {
                    @Override
                    public void onItemResponse(Customer createCustomer) {
                        //TODO
                        //successfully create a customer
                    }
                });
            }
        });
        return view;
    }


    private void setRecycler() {
        initRecyclerAdapter(mRecyclerView, mCardAdapter, mShoppingRepository.getProducts());
        Log.d(TAG, "setRecycler: " + mShoppingRepository.getProducts().size());
    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.card_recycler_view);
        mFinalShop = view.findViewById(R.id.final_shop);
        mSalePrice = view.findViewById(R.id.card_sale_price_fragment_shopping);
    }

    private void initRecyclerAdapter(RecyclerView recyclerView,
                                     CardAdapter cardAdapter,
                                     List<ProductsItem> productItems) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

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

        mSalePrice.setText(cardAdapter.getFinalPriceValue() +
                " " +
                getContext().getResources().getString(R.string.toman));

        Log.d(TAG, "updateRecyclerAdapter: " + cardAdapter.getFinalPriceValue());


    }

}