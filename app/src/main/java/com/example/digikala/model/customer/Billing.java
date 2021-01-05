package com.example.digikala.model.customer;

import com.google.gson.annotations.SerializedName;

public class Billing {

    @SerializedName("company")
    private String mCompany;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("address_1")
    private String mAddressOne;

    @SerializedName("address_2")
    private String mAddressTwo;

    @SerializedName("city")
    private String mCity;

    @SerializedName("state")
    private String mState;

    @SerializedName("postcode")
    private String mPostCode;

    @SerializedName("country")
    private String mCountry;

    public Billing(String company, String firstName, String lastName, String addressOne,
                   String addressTwo, String city, String state, String postCode,
                   String country, String email, String phone) {
        mCompany = company;
        mFirstName = firstName;
        mLastName = lastName;
        mAddressOne = addressOne;
        mAddressTwo = addressTwo;
        mCity = city;
        mState = state;
        mPostCode = postCode;
        mCountry = country;
        mEmail = email;
        mPhone = phone;
    }

    @SerializedName("email")
    private String mEmail;

    @SerializedName("phone")
    private String mPhone;


    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
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

    public String getAddressOne() {
        return mAddressOne;
    }

    public void setAddressOne(String addressOne) {
        mAddressOne = addressOne;
    }

    public String getAddressTwo() {
        return mAddressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        mAddressTwo = addressTwo;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getPostCode() {
        return mPostCode;
    }

    public void setPostCode(String postCode) {
        mPostCode = postCode;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
