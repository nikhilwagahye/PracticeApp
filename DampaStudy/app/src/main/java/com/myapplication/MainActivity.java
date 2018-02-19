package com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myapplication.ormlite.DatabaseActivity;
import com.myapplication.retrofit.APIActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonRetrofit;
    private Button buttonOrmlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRetrofit = (Button) findViewById(R.id.buttonRetrofit);
        buttonOrmlite = (Button) findViewById(R.id.buttonOrmlite);

        buttonRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, APIActivity.class);
                startActivity(intent);
            }
        });


        buttonOrmlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
}
