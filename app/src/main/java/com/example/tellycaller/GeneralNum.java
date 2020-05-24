package com.example.tellycaller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.Manifest.permission.CALL_PHONE;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralNum extends AppCompatActivity {

    DatabaseReference databaseReference;
    DatabaseReference   dbref;
    DatabaseReference feedbackref;
    FirebaseAuth genAuth;
    String onlineUser;
    Toolbar gtobar;
    RecyclerView recyclerView;
    Dialog UpdatedialogBox;
    TextView tv1, tv2;
    Spinner calsatus;
    public static EditText et, dateTimes;
    String[] st = {"Select Call Status", "N.I", "C.B", "C.A"};
    String Uname, Unum;
    String nm="";
    String mn="";
    Button cancle, update;
    ProgressBar progressBar;
    static String dnm,dmn,status,dttm,rmrk="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_num);

        genAuth = FirebaseAuth.getInstance();
        onlineUser = genAuth.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Created_Customer").child(onlineUser);
        dbref = FirebaseDatabase.getInstance().getReference().child("Created_Customer");
        UpdatedialogBox = new Dialog(this);
        progressBar = findViewById(R.id.pbr);
        gtobar = findViewById(R.id.gtobar);
        setSupportActionBar(gtobar);
        getSupportActionBar().setTitle("General");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.listitems);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
//        genAddapter = new GenAddapter(this);
//        recyclerView.setAdapter(genAddapter);
//        genAddapter.notifyDataSetChanged();
        //genAddapter = new GenAddapter(mobils,names,GeneralNum.this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        FirebaseRecyclerAdapter<CustomerModel, GenAddapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CustomerModel, GenAddapter>
                (CustomerModel.class, R.layout.gitemlayout, GenAddapter.class, databaseReference) {
            @Override
            protected void populateViewHolder(final GenAddapter genAddapter, final CustomerModel customerModel, final int i) {

                progressBar.setVisibility(View.GONE);
                TextView sr = genAddapter.mview.findViewById(R.id.snum);
                sr.setText((i + 1) + "");

                genAddapter.setUserName(customerModel.getName());
                genAddapter.setUserMobile(customerModel.getMobile_num());

                final TextView txtnum = genAddapter.mview.findViewById(R.id.cmobil);
                txtnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String calnum = customerModel.getMobile_num();

                        if (ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                            Intent calin = new Intent(Intent.ACTION_CALL);
                            calin.setData(Uri.parse("tel:" + calnum));
                            startActivity(calin);
                        }else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{CALL_PHONE},1);
                            }
                        }

                    }
                });

                Button updt = genAddapter.mview.findViewById(R.id.cupdate);
                updt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uname = getRef(i).getKey();
                        dbref.child(onlineUser).child(Uname).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                nm = dataSnapshot.child("Name").getValue().toString();
                                mn = dataSnapshot.child("Mobile_num").getValue().toString();

                                final AlertDialog.Builder alert = new AlertDialog.Builder(GeneralNum.this);
                                View genView = getLayoutInflater().inflate(R.layout.dialog_update, null);


                                tv1 = genView.findViewById(R.id.cusname);
                                tv1.setText(nm);
                                dnm = tv1.getText().toString();
                                tv2 = genView.findViewById(R.id.cusnumber);
                                tv2.setText(mn);
                                dmn = tv2.getText().toString();
                                calsatus = genView.findViewById(R.id.spnr);
                                calsatus.setAdapter(new ArrayAdapter<String>(GeneralNum.this, android.R.layout.simple_list_item_1, st));
                                calsatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        switch (position){
                                            case 1:
                                                int selectedItemofMyspinner = calsatus.getSelectedItemPosition();
                                                status = (String) calsatus.getItemAtPosition(selectedItemofMyspinner);
                                                Toast.makeText(GeneralNum.this, status, Toast.LENGTH_SHORT).show();
                                                break;
                                            case 2:
                                                int selectedItemofMyspinner1 = calsatus.getSelectedItemPosition();
                                                status = (String) calsatus.getItemAtPosition(selectedItemofMyspinner1);
                                                Toast.makeText(GeneralNum.this, status, Toast.LENGTH_SHORT).show();
                                                break;
                                            case 3:
                                                int selectedItemofMyspinner3 = calsatus.getSelectedItemPosition();
                                                status = (String) calsatus.getItemAtPosition(selectedItemofMyspinner3);
                                                Toast.makeText(GeneralNum.this, status, Toast.LENGTH_SHORT).show();
                                                break;
                                        }


                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                                et = genView.findViewById(R.id.rmark);

                                dateTimes = genView.findViewById(R.id.datetime);

                                cancle = genView.findViewById(R.id.cancl);
                                update = genView.findViewById(R.id.updt);
                                alert.setView(genView);

                                final AlertDialog alertDialog = alert.create();
                                alertDialog.setCanceledOnTouchOutside(false);

                                dateTimes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ShowTimePickerDialog();
                                        ShowDatePickerDialog();
                                    }
                                });

                                update.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        progressBar.setVisibility(View.VISIBLE);
                                        if (dateTimes.getText().toString().isEmpty() || et.getText().toString().isEmpty()) {
                                            dateTimes.setError("Select Date Time");
                                            et.setError("Type Remark");
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            //AddData()

                                            feedbackref = FirebaseDatabase.getInstance().getReference().child("FeedBack_Data").child(onlineUser);
                                            rmrk = et.getText().toString();
                                            Map diologMap = new HashMap();
                                            diologMap.put("Name",nm);
                                            diologMap.put("Mobile_num",mn);
                                            diologMap.put("Status",status);
                                            diologMap.put("Appointment_Dt_Tm",dttm);
                                            diologMap.put("Remarks",rmrk);
                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("FeedBack_Data").child(onlineUser).push();
                                            String UserKey = ref.getKey();

                                            Map bodymap = new HashMap();
                                            bodymap.put("Key"+UserKey,diologMap);

                                            feedbackref.updateChildren(bodymap, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                    if (databaseError == null){
                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(GeneralNum.this, "FeedBack Submitted", Toast.LENGTH_SHORT).show();
                                                        alertDialog.dismiss();

                                                    }
                                                }
                                            });



                                        }

                                    }
                                });

                                cancle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(GeneralNum.this, "Cancle", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                });


//                                diologMap.put("Call_status",)


                                alertDialog.show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                        //ShowUpdateDialogBox();
                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    private void ShowDatePickerDialog() {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    private void ShowTimePickerDialog() {

        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            // Use the current date as the default date in the picker

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it

            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Do something with the date chosen by the user

            dateTimes.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar t = Calendar.getInstance();
            int hour = t.get(Calendar.HOUR_OF_DAY);
            int min = t.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, min, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            // Do something with the time chosen by the user
            dateTimes.setText(dateTimes.getText() + " -" + hourOfDay + ":" + minute);
            dttm = dateTimes.getText().toString();
        }
    }
}

//   private Cursor getAllItem(){
//
//        return Create_Customer.db.query(DatabaseHelper.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//   }



