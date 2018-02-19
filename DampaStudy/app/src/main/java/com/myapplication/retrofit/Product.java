package com.myapplication.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikhilkumar.waghaye on 19-02-2018.
 */

public class Product {

    @SerializedName("perSqFtPrice")
    private String perSqFtPrice;

    @SerializedName("productImageURL")
    private String productImageURL;

    @SerializedName("productName")
    private String productName;

    @SerializedName("productType")
    private String productType;


    public String getPerSqFtPrice() {
        return perSqFtPrice;
    }

    public void setPerSqFtPrice(String perSqFtPrice) {
        this.perSqFtPrice = perSqFtPrice;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


}
