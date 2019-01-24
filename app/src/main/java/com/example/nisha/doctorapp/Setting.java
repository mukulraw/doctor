package com.example.nisha.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    TextView logout;

    TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        logout = findViewById(R.id.textView75);
        change= findViewById(R.id.textView76);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Setting.this , ChangePassword.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Setting.this , Login.class);
                startActivity(i);
            }
        });

    }
}
