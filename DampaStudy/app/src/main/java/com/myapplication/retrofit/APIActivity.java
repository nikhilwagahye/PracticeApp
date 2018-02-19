package com.myapplication.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.myapplication.R;
import com.myapplication.retrofit.service.RestClient;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIActivity extends AppCompatActivity {

    private ProgressDialog progrssDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        /* 1. Add retrofit dependencies to grdle
           2. Make RetrofitUtil and Restclient Class in service package
           3. Make ApiService interface, which will have GET Property and URL and response type. Always, in retrofit, Call class is used to fetch response.
           4. Make Model as per response of API. Here, For Ex, Product model is made. We have to use  @SerializedName("key ")   above each field.
           5. In Response, its giving list of products so used, Call<List<Product>> methodName
           6. To call API use below code.
           7. Add permissions of internet in manifest.
        */

        progrssDialog = new ProgressDialog(APIActivity.this);
        progrssDialog.setMessage("Please wait");
        progrssDialog.setIndeterminate(true);
        progrssDialog.setCancelable(false);
        progrssDialog.show();
        Call<List<Product>> productsCall = RestClient.getApiService(ApiConstants.BASE_URL).getAllProducts();

        productsCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    //
                    List<Product> productList = response.body();
                    // set this to recycler view adapter


                } else {
                }


                progrssDialog.cancel();

            }


            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                if (t != null) {
                    progrssDialog.cancel();

                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());

                }
            }
        });


    }
}
