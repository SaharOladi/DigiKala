package com.example.digikala.fragment;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.digikala.R;
import com.example.digikala.adapter.SliderAdapter;
import com.example.digikala.model.product.ImagesItem;
import com.example.digikala.model.product.ProductsItem;
import com.example.digikala.repository.Repository;
import com.example.digikala.repository.ShoppingRepository;
import com.example.digikala.utils.ShoppingPreferences;
import com.smarteist.autoimageslider.SliderView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.util.List;

public class ProductDetailFragment extends Fragment {
    public static final String ARGS_PRODUCT = "ARGS_PRODUCT_DETAIL";

    private Repository mRepository;
    private ShoppingRepository mShoppingRepository;
    private ProductsItem mProduct;

    private SliderAdapter mSliderAdapter;
    private SliderView mSliderView;

    private TextView mRegularPrice, mFinalePrice, mDescription, mName;
    private Button mAddToBag;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public static ProductDetailFragment newInstance(ProductsItem productsItem) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_PRODUCT, productsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository(getContext());
        mShoppingRepository = ShoppingRepository.getInstance(getContext());
        mProduct = (ProductsItem) getArguments().get(ARGS_PRODUCT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        findViews(view);
        initViews();
        setListener();

        return view;
    }

    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.fragment_home_slider);
        mRegularPrice = view.findViewById(R.id.old_price);
        mDescription = view.findViewById(R.id.product_detail_description);
        mFinalePrice = view.findViewById(R.id.latest_price);
        mAddToBag = view.findViewById(R.id.add_to_shop);
        mName = view.findViewById(R.id.product_detail_name);
    }

    private void initViews() {
        mFinalePrice.setText(mProduct.getPrice() + " " +
                getContext().getResources().getString(R.string.toman));
        mRegularPrice.setText(mProduct.getRegularPrice() + " " +
                getContext().getResources().getString(R.string.toman));
        mRegularPrice.setPaintFlags(mRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mDescription.setText(getDescription());
        mName.setText(mProduct.getName());
        setupSliderAdapter(mProduct.getImages());

    }

    private String getDescription() {
        String description = mProduct.getDescription();
        if (description.equals(null))
            return description;
        Document document = Jsoup.parse(description);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    private void setListener() {
        mAddToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShoppingRepository.insertProduct(mProduct);
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ShoppingFragment.newInstance())
                        .commit();
            }
        });
    }

    private void setupSliderAdapter(List<ImagesItem> imagesItems) {
        mSliderAdapter = new SliderAdapter(getContext(), imagesItems);
        mSliderView.setSliderAdapter(mSliderAdapter);
    }

}