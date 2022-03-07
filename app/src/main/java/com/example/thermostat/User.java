package com.example.thermostat;

import android.widget.EditText;

public class User {
    public EditText username;
    public EditText e_mail;
    public User(){

    }
    public User(EditText username, EditText e_mail){
        this.username= username;
        this.e_mail=e_mail;
    }
}
