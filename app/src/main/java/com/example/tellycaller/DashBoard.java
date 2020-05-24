package com.example.tellycaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {

    CardView c1,c2,c3,c4,c5;
    ImageView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board2);


        c1 = findViewById(R.id.crd);
        c2 = findViewById(R.id.crd2);
        c3 = findViewById(R.id.crd3);
        c4 = findViewById(R.id.crd4);

        logout = findViewById(R.id.logout);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in1= new Intent(DashBoard.this, Create_Customer.class);
                startActivity(in1);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(DashBoard.this,GeneralNum.class);
                startActivity(in2);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in3 = new Intent(DashBoard.this,FolowNumber.class);
                startActivity(in3);

            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent importdata = new Intent(DashBoard.this,ExcelData.class);
                startActivity(importdata);


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent out = new Intent(DashBoard.this,MainActivity.class);
                out.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(out);
            }
        });

    }
}
