package com.example.digikala.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.digikala.R;

public class SplashActivity extends AppCompatActivity {

    public static final int DELAY_MILLIS = 1000;
    private LottieAnimationView mAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAnim = findViewById(R.id.anim_splash);

        //TODO: check internet and request to net
        mAnim.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.newIntent(SplashActivity.this));
                finish();
            }
        }, DELAY_MILLIS);
    }
}