package com.example.digikala.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digikala.R;
import com.example.digikala.adapter.ProductAdapter;
import com.example.digikala.model.product.CategoriesItem;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment{

    public static final String TAG = "CategoryFragment";

    private RecyclerView mRecyclerViewCategoryOne, mRecyclerViewCategoryTwo,
            mRecyclerViewCategoryThree, mRecyclerViewCategoryFour, mRecyclerViewCategoryFive,
            mRecyclerViewCategorySix, mRecyclerViewCategorySeven, mRecyclerViewCategoryEight,
            mRecyclerViewCategoryNine, mRecyclerViewCategoryTen;

    private ProductAdapter mAdapterOne, mAdapterTwo, mAdapterThree, mAdapterFour, mAdapterFive,
            mAdapterSix, mAdapterSeven, mAdapterEight, mAdapterNine, mAdapterTen;

    private TextView mTextOne, mTextTwo, mTextThree, mTextFour, mTextFive, mTextSix, mTextSeven,
            mTextEight, mTextNine, mTextTen;

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
        mRepository = new Repository(getContext());

        mRepository.fetchCategory(1, new Repository.CategoryCallbacks() {
            @Override
            public void onItemResponse(List<CategoriesItem> items) {
                updateCategoryRecyclerAdapter(initCategoryIds(items));
                setTextName(items);
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
        mRecyclerViewCategoryOne = view.findViewById(R.id.category_recycler_one);
        mRecyclerViewCategoryTwo = view.findViewById(R.id.category_recycler_two);
        mRecyclerViewCategoryThree = view.findViewById(R.id.category_recycler_three);
        mRecyclerViewCategoryFour = view.findViewById(R.id.category_recycler_four);
        mRecyclerViewCategoryFive = view.findViewById(R.id.category_recycler_five);
        mRecyclerViewCategorySix = view.findViewById(R.id.category_recycler_six);
        mRecyclerViewCategorySeven = view.findViewById(R.id.category_recycler_seven);
        mRecyclerViewCategoryEight = view.findViewById(R.id.category_recycler_eight);
        mRecyclerViewCategoryNine = view.findViewById(R.id.category_recycler_nine);
        mRecyclerViewCategoryTen = view.findViewById(R.id.category_recycler_ten);

        mTextOne = view.findViewById(R.id.category_name_one);
        mTextTwo = view.findViewById(R.id.category_name_two);
        mTextThree = view.findViewById(R.id.category_name_three);
        mTextFour = view.findViewById(R.id.category_name_four);
        mTextFive = view.findViewById(R.id.category_name_five);
        mTextSix = view.findViewById(R.id.category_name_six);
        mTextSeven = view.findViewById(R.id.category_name_seven);
        mTextEight = view.findViewById(R.id.category_name_eight);
        mTextNine = view.findViewById(R.id.category_name_nine);
        mTextTen = view.findViewById(R.id.category_name_ten);

    }

    private void updateCategoryRecyclerAdapter(List<Integer> ids) {
        mRepository.fetchCategoryProduct(1, ids.get(0), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterOne == null) {
                    mAdapterOne = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryOne.setAdapter(mAdapterOne);
                } else {
                    mAdapterOne.setProductsItem(items);
                    mAdapterOne.notifyDataSetChanged();
                }
            }
        });

        mRepository.fetchCategoryProduct(1, ids.get(1), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterTwo == null) {
                    mAdapterTwo = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryTwo.setAdapter(mAdapterTwo);
                } else {
                    mAdapterTwo.setProductsItem(items);
                    mAdapterTwo.notifyDataSetChanged();
                }
            }
        });

        mRepository.fetchCategoryProduct(1, ids.get(2), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterThree == null) {
                    mAdapterThree = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryThree.setAdapter(mAdapterThree);
                } else {
                    mAdapterThree.setProductsItem(items);
                    mAdapterThree.notifyDataSetChanged();
                }
            }
        });

        mRepository.fetchCategoryProduct(1, ids.get(3), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterFour == null) {
                    mAdapterFour = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryFour.setAdapter(mAdapterFour);
                } else {
                    mAdapterFour.setProductsItem(items);
                    mAdapterFour.notifyDataSetChanged();
                }
            }
        });
        mRepository.fetchCategoryProduct(1, ids.get(4), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterFive == null) {
                    mAdapterFive = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryFive.setAdapter(mAdapterFive);
                } else {
                    mAdapterFive.setProductsItem(items);
                    mAdapterFive.notifyDataSetChanged();
                }
            }
        });
        mRepository.fetchCategoryProduct(1, ids.get(5), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterSix == null) {
                    mAdapterSix = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategorySix.setAdapter(mAdapterSix);
                } else {
                    mAdapterSix.setProductsItem(items);
                    mAdapterSix.notifyDataSetChanged();
                }
            }
        });
        mRepository.fetchCategoryProduct(1, ids.get(6), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterSeven == null) {
                    mAdapterSeven = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategorySeven.setAdapter(mAdapterSeven);
                } else {
                    mAdapterSeven.setProductsItem(items);
                    mAdapterSeven.notifyDataSetChanged();
                }
            }
        });
        mRepository.fetchCategoryProduct(1, ids.get(7), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterEight == null) {
                    mAdapterEight = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryEight.setAdapter(mAdapterEight);
                } else {
                    mAdapterEight.setProductsItem(items);
                    mAdapterEight.notifyDataSetChanged();
                }
            }
        });
        mRepository.fetchCategoryProduct(1, ids.get(8), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterNine == null) {
                    mAdapterNine = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryNine.setAdapter(mAdapterNine);
                } else {
                    mAdapterNine.setProductsItem(items);
                    mAdapterNine.notifyDataSetChanged();
                }
            }
        });
        mRepository.fetchCategoryProduct(1, ids.get(9), new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                if (mAdapterTen == null) {
                    mAdapterTen = new ProductAdapter(getContext(), items);
                    mRecyclerViewCategoryTen.setAdapter(mAdapterTen);
                } else {
                    mAdapterTen.setProductsItem(items);
                    mAdapterTen.notifyDataSetChanged();
                }
            }
        });
    }


    private List<Integer> initCategoryIds(List<CategoriesItem> items) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (!ids.contains(items.get(i).getId())) {
                ids.add(items.get(i).getId());
            }
        }
        return ids;
    }

    private void setTextName(List<CategoriesItem> items) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (!names.equals(items.get(i).getName())) {
                names.add(items.get(i).getName());
            }
        }
        mTextOne.setText(names.get(0));
        mTextTwo.setText(names.get(1));
        mTextThree.setText(names.get(2));
        mTextFour.setText(names.get(3));
        mTextFive.setText(names.get(4));
        mTextSix.setText(names.get(5));
        mTextSeven.setText(names.get(6));
        mTextEight.setText(names.get(7));
        mTextNine.setText(names.get(8));
        mTextTen.setText(names.get(9));

    }


}