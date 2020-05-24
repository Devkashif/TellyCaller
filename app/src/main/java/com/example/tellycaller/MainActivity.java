package com.example.tellycaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText idt,passw;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            setContentView(R.layout.activity_main);

            idt = findViewById(R.id.id);
//        passw = findViewById(R.id.pass);
            login = findViewById(R.id.sign);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String number = idt.getText().toString();
                    if (number.isEmpty() || number.length()<10){

                        idt.setError("Valid number is required");
                        idt.requestFocus();
                        return;

                    }

                    String phonenumber = "+91"+number;

                    Intent intent = new Intent(MainActivity.this, OTP.class);
                    intent.putExtra("phonenumber",phonenumber);
                    startActivity(intent);
                    finish();
                }
            });
        } catch(Exception e){
            if(e.getCause().getClass().equals(AssertionError.class)){
                // handle your exception  1
            } else {
                // handle the rest of the world exception
            }
        }



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this,DashBoard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
