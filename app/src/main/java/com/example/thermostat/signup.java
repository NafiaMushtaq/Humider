package com.example.thermostat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    private FirebaseAuth thermostat;

    EditText username, password, repassword, e_mail;
    Button btnsignup1;
    TextView loginlink2;
    ImageView socialicons1, socialicons2;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        thermostat = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.username);
        e_mail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnsignup1 = (Button) findViewById(R.id.btnsignup1);
        loginlink2 = (TextView) findViewById(R.id.loginlink2);
        socialicons1 = (ImageView) findViewById(R.id.socialicons1);
        socialicons2 = (ImageView) findViewById(R.id.socialicons2);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);


        btnsignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String email = e_mail.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = repassword.getText().toString().trim();

                if (user.equals("") || email.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(signup.this, "Please enter all the details ", Toast.LENGTH_SHORT).show();
                else {
                    if (user.matches("^([a-zA-Z]{2,10}\\s[a-zA-Z]{1,10}'?-?[a-zA-Z]{2,10}\\s?([a-zA-Z]{1,10})?)")) {
                        if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                            if (pass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                                if (repass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                                    if (pass.equals(repass)) {
                                        progressbar.setVisibility(View.GONE);
                                        thermostat.createUserWithEmailAndPassword(email,pass)
                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()){
                                                            User user = new User(username,e_mail);
                                                            FirebaseDatabase.getInstance().getReference("Users")
                                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()){
                                                                                Toast.makeText(signup.this, "Reqister successfully", Toast.LENGTH_SHORT).show();

                                                                                progressbar.setVisibility(view.GONE);
                                                                            }else{
                                                                                Toast.makeText(signup.this, "Registration failed! Try Again", Toast.LENGTH_SHORT).show();
                                                                                progressbar.setVisibility(view.GONE);
                                                                            }
                                                                        }
                                                                    });
                                                        }else{
                                                            e_mail.setError("Email Already exists\\n\"+ \"Signup with another email Address");
                                                            progressbar.setVisibility(view.GONE);
                                                        }
                                                    }
                                                });

                                    } else {
                                        password.setError("Password does not match");
                                    }
                                } else {
                                    repassword.setError("Password must be minimum 8 characters\n" + "at least 1 digit and 1 letter\n" + "no white spaces\n");
                                }
                            } else {
                                password.setError("Password must be minimum 8 characters\n" + "at least 1 digit and 1 letter\n" + "no white spaces\n");
                            }
                        } else {
                            e_mail.setError("Write  correct email address\n" + "E.g:xyz@gmail.com");
                        }
                    }else{
                        username.setError("Write First and Last Name\n" + "E.g: Nafia Mehmood");
                    }
                }
            }
        });
        loginlink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, login.class));

            }
        });
    }


}