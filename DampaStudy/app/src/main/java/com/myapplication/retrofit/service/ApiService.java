package com.myapplication.retrofit.service;

import com.myapplication.retrofit.ApiConstants;
import com.myapplication.retrofit.Product;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @GET(ApiConstants.PRODUCT_URL)
    Call<List<Product>> getAllProducts();
}
