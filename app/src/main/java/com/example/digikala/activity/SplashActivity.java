package com.example.digikala.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.digikala.R;
import com.example.digikala.model.ProductsItem;
import com.example.digikala.network.NetworkGetter;
import com.example.digikala.repository.Repository;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements NetworkGetter.NetworkState {

    public static final int DELAY_MILLIS = 5000;
    public static final String TAG = "MySplashActivity";

    private LottieAnimationView mAnim;
    private Runnable mSplashRunnable;
    private Snackbar mSnackBar;
    private Handler splashHandler;

    private Repository mRepository;
    private NetworkGetter mNetworkGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mNetworkGetter = new NetworkGetter();
        mRepository = new Repository(SplashActivity.this);
        RequestItemFromInternet();

        initialAnimation();
        splashHandler = new Handler();
        mSplashRunnable = initialRunnable();
        startMainActivity(mSplashRunnable);

    }

    private void initialAnimation() {
        mAnim = findViewById(R.id.anim_splash);
        mAnim.playAnimation();
    }

    private Runnable initialRunnable() {
        return new Runnable() {
            public void run() {
                startActivity(MainActivity.newIntent(SplashActivity.this));
                finish();
            }
        };
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private void startMainActivity(Runnable runnable) {
        if (isNetworkAvailable()) {
            splashHandler.postDelayed(runnable, DELAY_MILLIS);
        } else {
            showSnackBar();
        }
    }

    private void showSnackBar() {
        View snackBarLayout = initialSnackBar();
        TextView textView = initialTextViewSnackBar(snackBarLayout);

        ViewCompat.setLayoutDirection(mSnackBar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.retry);
                textView.setTextColor(Color.BLACK);
                Typeface typeface = ResourcesCompat.getFont(SplashActivity.this, R.font.iran_yekan);
                textView.setTypeface(typeface);
                startMainActivity(mSplashRunnable);
            }
        });
        mSnackBar.show();
        mAnim.pauseAnimation();
    }

    private TextView initialTextViewSnackBar(View snackBarLayout) {
        TextView textView = snackBarLayout.findViewById(R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_retry_foreground,
                0,
                0,
                0);
        textView.setText(R.string.connection_fail);
        textView.setTextColor(Color.BLACK);
        Typeface typeface = ResourcesCompat.getFont(SplashActivity.this, R.font.iran_yekan);
        textView.setTypeface(typeface);
        return textView;
    }

    private View initialSnackBar() {
        mSnackBar = Snackbar.make(findViewById(R.id.anim_splash),
                getString(R.string.connection_fail),
                Snackbar.LENGTH_INDEFINITE);
        mSnackBar.setBackgroundTint(Color.WHITE);
        return mSnackBar.getView();
    }

    private void RequestItemFromInternet() {
        mNetworkGetter.fetchSingleProduct(608, new NetworkGetter.SingleCallbacks() {
            @Override
            public void onItemResponse(ProductsItem item) {
                mRepository.setSingleProductsItem(item);
                Log.d(TAG, "onItemResponse: "+mRepository.getSingleProductsItem().toString());
            }
        });
        mNetworkGetter.fetchAllProductItemsAsync(new NetworkGetter.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                mRepository.setAllProductsItems(items);
                Log.d(TAG, "onItemResponse: "+mRepository.getAllProductsItems().size());
            }
        });
        mNetworkGetter.fetchMostVisitedProducts(1, new NetworkGetter.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                mRepository.setMostVisitedProductsItems(items);
                Log.d(TAG, "onItemResponse: "+mRepository.getMostVisitedProductsItems().size());
            }
        });
        mNetworkGetter.fetchRatedProducts(1, new NetworkGetter.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                mRepository.setRatedProductsItems(items);
            }
        });
        mNetworkGetter.fetchRecentProducts(1, new NetworkGetter.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                mRepository.setRecentProductsItems(items);
                Log.d(TAG, "onItemResponse: "+mRepository.getRecentProductsItems().size());
            }
        });
    }

    @Override
    public void onFailureCalled() {
        showSnackBar();
    }


}