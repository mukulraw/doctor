package com.example.nisha.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    EditText email , pass , ph , cp ;

    TextView login ;

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        ph = findViewById(R.id.mobile);
        cp = findViewById(R.id.cp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Signup.this , Login.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Signup.this , Login.class);
                startActivity(i);

            }
        });
    }
}
