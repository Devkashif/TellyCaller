package com.example.tellycaller;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;


//import com.google.firestore.v1beta1.StructuredQuery;

public class Offline_Capability extends Application {

    DatabaseReference userRefrence;
    FirebaseAuth userAuth;
    FirebaseUser curentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //Load Profile picture Offline - Picasso

//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
//        Picasso.setSingletonInstance(built);

        userAuth = FirebaseAuth.getInstance();
        curentUser = userAuth.getCurrentUser();


    }
}
