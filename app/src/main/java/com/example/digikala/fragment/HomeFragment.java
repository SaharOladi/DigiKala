package com.example.digikala.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.digikala.IOnBackPress;
import com.example.digikala.R;
import com.example.digikala.adapter.CategoryAdapter;
import com.example.digikala.adapter.ProductAdapter;
import com.example.digikala.adapter.SliderAdapter;
import com.example.digikala.model.product.CategoriesItem;
import com.example.digikala.model.product.ImagesItem;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.Repository;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class HomeFragment extends Fragment implements IOnBackPress {

    public static final String TAG = "HomeFragment";

    private SliderView mSliderView;
    private SliderAdapter mSliderAdapter;

    private EditText mSearch;


    private RecyclerView mRecyclerViewRecentProduct, mRecyclerViewMostVisitedProduct,
            mRecyclerViewRatedProduct, mRecyclerCategoryProduct, mRecyclerViewAmazing;


    private ProductAdapter mRecentProductAdapter, mMostVisitedProductAdapter,
            mRatedProductAdapter, mAmazingAdapter;

    private CategoryAdapter mCategoryAdapter;


    private Repository mRepository;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);
        updateRecycler();

        setSearchListener();

        return view;
    }

    private void updateRecycler() {
        mRepository.fetchSingleProduct(608, new Repository.SingleCallbacks() {
            @Override
            public void onItemResponse(ProductsItem item) {
                setupSliderAdapter(item.getImages());
            }
        });

        mRepository.fetchRecentProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewRecentProduct, mRecentProductAdapter, items);
            }
        });

        mRepository.fetchMostVisitedProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewMostVisitedProduct, mMostVisitedProductAdapter, items);
            }
        });

        mRepository.fetchRatedProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewRatedProduct, mRatedProductAdapter, items);
            }
        });
        mRepository.fetchRatedProducts(2, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewAmazing, mAmazingAdapter, items);
            }
        });
        mRepository.fetchCategory(1, new Repository.CategoryCallbacks() {
            @Override
            public void onItemResponse(List<CategoriesItem> items) {
                initCategoryRecyclerAdapter(mRecyclerCategoryProduct, mCategoryAdapter, items);
            }
        });
    }


    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.fragment_home_slider);
        mRecyclerViewRecentProduct = view.findViewById(R.id.fragment_home_recyclerview_recent_item);
        mRecyclerViewMostVisitedProduct = view.findViewById(R.id.fragment_home_recyclerview_most_visited);
        mRecyclerViewRatedProduct = view.findViewById(R.id.fragment_home_recyclerview_top_rated);
        mRecyclerCategoryProduct = view.findViewById(R.id.fragment_home_recyclerview_category);
        mRecyclerViewAmazing = view.findViewById(R.id.fragment_home_recyclerview_offered_item);
        mSearch = view.findViewById(R.id.searching_query);

    }

    private void initRecyclerAdapter(RecyclerView recyclerView,
                                     ProductAdapter productAdapter,
                                     List<ProductsItem> productsItems) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRecyclerAdapter(recyclerView, productAdapter, productsItems);
    }

    private void updateRecyclerAdapter(RecyclerView recyclerView,
                                       ProductAdapter productAdapter,
                                       List<ProductsItem> productsItems) {

        if (productAdapter == null) {
            productAdapter = new ProductAdapter(getContext(), productsItems);
            recyclerView.setAdapter(productAdapter);
        } else {
            productAdapter.setProductsItem(productsItems);
            productAdapter.notifyDataSetChanged();
        }
    }


    private void setupSliderAdapter(List<ImagesItem> imagesItems) {
        mSliderAdapter = new SliderAdapter(getContext(), imagesItems);
        mSliderView.setSliderAdapter(mSliderAdapter);
        mSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void initCategoryRecyclerAdapter(RecyclerView recyclerView,
                                             CategoryAdapter categoryAdapter,
                                             List<CategoriesItem> categoriesItems) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRecyclerCategoryAdapter(recyclerView, categoryAdapter, categoriesItems);
    }

    private void updateRecyclerCategoryAdapter(RecyclerView recyclerView,
                                               CategoryAdapter categoryAdapter,
                                               List<CategoriesItem> categoriesItems) {

        if (categoryAdapter == null) {
            categoryAdapter = new CategoryAdapter(getContext(), categoriesItems);
            recyclerView.setAdapter(categoryAdapter);
        } else {
            categoryAdapter.setCategoriesItem(categoriesItems);
            categoryAdapter.notifyDataSetChanged();
        }
    }


    private void setSearchListener() {
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            public void afterTextChanged(Editable s) {
                getQueryEditText(s.toString());
            }
        });


    }


    private void getQueryEditText(String query) {

        if (query.length() > 2)
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment.newInstance(query))
                    .commit();
    }


    @Override
    public boolean onBackPressed() {
        return true;
    }
}