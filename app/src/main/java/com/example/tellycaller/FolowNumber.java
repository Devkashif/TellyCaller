package com.example.tellycaller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.CALL_PHONE;

public class FolowNumber extends AppCompatActivity {

    DatabaseReference Fdbreference;
    FirebaseAuth fAuth;
    String curentUserId;
    RecyclerView FrecyclerView;
    ProgressBar progressBar;
    Toolbar ftobar;
    FirebaseRecyclerAdapter<FollowModel, FolowNumberHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folow_number);

        ftobar = findViewById(R.id.ftobars);
        progressBar = findViewById(R.id.progs);
        fAuth = FirebaseAuth.getInstance();
        curentUserId = fAuth.getCurrentUser().getUid();

        setSupportActionBar(ftobar);
        getSupportActionBar().setTitle("Follow Numbers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fdbreference = FirebaseDatabase.getInstance().getReference().child("FeedBack_Data").child(curentUserId);
        progressBar.setVisibility(View.VISIBLE);
        FrecyclerView = findViewById(R.id.listitem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        FrecyclerView.setLayoutManager(linearLayoutManager);




    }

    @Override
    protected void onStart() {
        super.onStart();



        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<FollowModel, FolowNumberHolder>
                (FollowModel.class, R.layout.followlayout, FolowNumberHolder.class, Fdbreference) {
            @Override
            protected void populateViewHolder(final FolowNumberHolder folowNumberHolder, final FollowModel followModel, int i) {

                progressBar.setVisibility(View.GONE);



                String kys = getRef(i).getKey();
                Fdbreference.child(kys).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String nm = dataSnapshot.child("Name").getValue().toString();
                        String mn = dataSnapshot.child("Mobile_num").getValue().toString();
                        String st = dataSnapshot.child("Status").getValue().toString();
                        String dttms = dataSnapshot.child("Appointment_Dt_Tm").getValue().toString();
                        String rmrks = dataSnapshot.child("Remarks").getValue().toString();
                        folowNumberHolder.setName(nm);
                        folowNumberHolder.setMobile_num(mn,st);
                        folowNumberHolder.setAppointment_Dt_Tm(dttms);
                        folowNumberHolder.setRemarks(rmrks);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

        };

        FrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


}

//    public static class FolowNumberHolder extends RecyclerView.ViewHolder{
//
//
//}
