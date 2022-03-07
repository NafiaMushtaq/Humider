package com.example.thermostat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText e_mail1, password1;
    TextView forgotpassword;
    Button btnlogin1;
    TextView signuplink2;
    ImageView socialicons1, socialicons2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e_mail1 = (EditText) findViewById(R.id.email1);
        password1 = (EditText) findViewById(R.id.password1);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        btnlogin1 = (Button) findViewById(R.id.btnlogin1);
        signuplink2 = (TextView) findViewById(R.id.signuplink2);
        socialicons1 = (ImageView) findViewById(R.id.socialicons1);
        socialicons2 = (ImageView) findViewById(R.id.socialicons2);


        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = e_mail1.getText().toString().trim();
                String pass1 = password1.getText().toString().trim();
                if (email1.equals("") || pass1.equals(""))
                    Toast.makeText(login.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                else {
                    if (email1.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        if (pass1.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {

                                Toast.makeText(login.this, "Sign IN successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), home.class);
                                startActivity(intent);

                        } else {
                            password1.setError("Password must be minimum 8 characters\n" + "at least 1 number\n" + "no white spaces\n");
                        }
                    } else {
                        e_mail1.setError("Write  correct email address\n" + "E.g:xyz@gmail.com");
                    }
                }
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, forgotpassword.class));
            }
        });
        signuplink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, signup.class));
            }
        });

    }
}