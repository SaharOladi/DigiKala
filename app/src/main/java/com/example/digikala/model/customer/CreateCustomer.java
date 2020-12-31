package com.example.digikala.model.customer;

import com.google.gson.annotations.SerializedName;

public class CreateCustomer {
    @SerializedName("email")
    private String mEmail;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("username")
    private String mUserName;

    @SerializedName("billing")
    private CustomerBilling mBilling;

    @SerializedName("shipping")
    private CustomerShipping mShipping;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public CustomerBilling getBilling() {
        return mBilling;
    }

    public void setBilling(CustomerBilling billing) {
        mBilling = billing;
    }

    public CustomerShipping getShipping() {
        return mShipping;
    }

    public void setShipping(CustomerShipping shipping) {
        mShipping = shipping;
    }
}
