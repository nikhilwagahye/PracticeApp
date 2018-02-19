package com.myapplication.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myapplication.R;
import com.myapplication.ormlite.orm.DatabaseUtil;
import com.myapplication.ormlite.orm.model.User;

import java.sql.SQLException;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private Button buttonInsert;
    private Button buttonGet;
    private Button buttonUpdate;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);


        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = new User();
                    user.setUserName("Nikhil");
                    user.setLastName("Waghaye");
                    user.setAddress("Bangalore");
                    user.setAge(27);


                    int id = DatabaseUtil.insertUser(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<User> users = DatabaseUtil.getAllUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = DatabaseUtil.updateUser("Nikhil");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseUtil.clearUserTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
