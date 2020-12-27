package com.example.digikala.activity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.digikala.R;

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}