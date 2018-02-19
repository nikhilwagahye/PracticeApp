package com.myapplication.ormlite.orm.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by nikhilkumar.waghaye on 19-02-2018.
 */

public class User {

    @DatabaseField(generatedId = true, columnName = "id")  // generateId--> autoincrement
    int userId;

    @DatabaseField(columnName = "userName")
    private String userName;

    @DatabaseField(columnName = "lastName")
    private String lastName;

    @DatabaseField(columnName = "age")
    private int age;

    @DatabaseField(columnName = "address")
    private String address;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
