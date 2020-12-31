package com.example.digikala.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.example.digikala.R;


public class OrderDialogFragment extends DialogFragment {

    public static final String ARGS_ID_ORDER_DIALOG = "ARGS_ID_ORDER_DIALOG";

    public static final int RESULT_CODE_ORDER_DIALOG_FRAGMENT = 20;
    public static final String EXTRA_ORDER_DIALOG_FRAGMENT = "com.example.digikala.EXTRA_ORDER_DIALOG_FRAGMENT";
    public static final String TAG = "OrderDialogFragment";

    private RadioButton mPopular, mNewest, mLowToHigh, mHighToLow;
    private String mOrderBy = "";


    public OrderDialogFragment() {
        // Required empty public constructor
    }

    public static OrderDialogFragment newInstance() {
        OrderDialogFragment fragment = new OrderDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_order_dialog, null);

        findDialogViews(view);
        setListener();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setPositiveButton("اعمال مرتب سازی", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(mOrderBy);
                        Log.d(TAG, "onClick: " + mOrderBy);
                    }
                })
                .setNegativeButton("خیر", null)
                .setView(view)
                .create();

        return alertDialog;
    }



    private void findDialogViews(View view) {
        mPopular = view.findViewById(R.id.popularity);
        mNewest = view.findViewById(R.id.latest);
        mHighToLow = view.findViewById(R.id.high_to_low);
        mLowToHigh = view.findViewById(R.id.low_to_high);
    }

    private void setListener() {
        mPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderBy = "popularity";
            }
        });
        mNewest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderBy = "date";
            }
        });

        mLowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderBy = "price";
            }
        });
        mHighToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderBy = "price-desc";
            }
        });
    }

    private void sendResult(String string) {
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = RESULT_CODE_ORDER_DIALOG_FRAGMENT;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ORDER_DIALOG_FRAGMENT, string);
        fragment.onActivityResult(requestCode, resultCode, intent);
    }
}