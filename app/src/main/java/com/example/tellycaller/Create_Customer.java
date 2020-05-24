package com.example.tellycaller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tellycaller.DatabaseHelper.COL1;
import static com.example.tellycaller.DatabaseHelper.COL2;
import static com.example.tellycaller.DatabaseHelper.COL3;
import static com.example.tellycaller.DatabaseHelper.COL4;
import static com.example.tellycaller.DatabaseHelper.TABLE_NAME;

public class Create_Customer extends AppCompatActivity {

    private static final String TAG = "Create_Customer";

    public static DatabaseHelper mdatabaseHelper;
    public static SQLiteDatabase db;
    DatabaseReference databaseReference;
    EditText et1,et2,et3,et4;
    Button btncrt;
    Toolbar crtTobar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__customer);

        progressBar = findViewById(R.id.prog);
        crtTobar = findViewById(R.id.tbr);
        setSupportActionBar(crtTobar);
        getSupportActionBar().setTitle("Create Customer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mdatabaseHelper = new DatabaseHelper(this);
        db = mdatabaseHelper.getWritableDatabase();
        et1 = findViewById(R.id.incusname);
        et2 = findViewById(R.id.mnum);
        et3 = findViewById(R.id.emailinput);
        et4 = findViewById(R.id.addres);

        btncrt = findViewById(R.id.crtbtn);

        btncrt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {


                String nm = et1.getText().toString();
                String mobilenum = et2.getText().toString();
                String emails = et3.getText().toString();
                String add = et4.getText().toString();

                if (nm.isEmpty() || mobilenum.isEmpty() || emails.isEmpty() || add.isEmpty()){
                    Toast.makeText(Create_Customer.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }else {

                    progressBar.setVisibility(View.VISIBLE);
                    FirebaseUser curentuser = FirebaseAuth.getInstance().getCurrentUser();
                    String Uid = curentuser.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Created_Customer").child(Uid);
                    Map hashMap = new HashMap<>();
                    hashMap.put("Name",nm);
                    hashMap.put("Mobile_num",mobilenum);
                    hashMap.put("Email",emails);
                    hashMap.put("Addresh",add);
                    DatabaseReference reference = databaseReference.child("Created_Customer").child(Uid).push();
                    String userKey = reference.getKey();

                    Map bodyMap = new HashMap<>();
                    bodyMap.put("Key"+ userKey,hashMap);


                   databaseReference.updateChildren(bodyMap, new DatabaseReference.CompletionListener() {
                       @Override
                       public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                           Intent in = new Intent();
                           setResult(Activity.RESULT_OK,in);
                           progressBar.setVisibility(View.GONE);
                           finish();
                           Toast.makeText(Create_Customer.this, "User Created", Toast.LENGTH_SHORT).show();


                       }
                   });
                   // AddData(customerModel);

                }

            }
        });
    }

////    public void AddData(CustomerModel customerModel){
////
////        db = mdatabaseHelper.getWritableDatabase();
////
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(COL2,customerModel.Cname);
////        contentValues.put(DatabaseHelper.COL3,customerModel.Cmobile);
////        contentValues.put(DatabaseHelper.COL4,customerModel.Cemail);
////        contentValues.put(DatabaseHelper.COL5,customerModel.Caddress);
////
////        db.insertOrThrow(TABLE_NAME, null, contentValues);
//
//       // Log.e("DATA", String.valueOf(db.insert(TABLE_NAME, null, contentValues)));
//
////        long id = db.insert(TABLE_NAME, null, contentValues);
//
//    }

//    public static List<CustomerModel> getAllUser(){
//        List<CustomerModel> customerModelList = new ArrayList<>();
//
//        String USER_DETAIL_QUERY = "SELECT * FROM " + TABLE_NAME;
//        db = mdatabaseHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(USER_DETAIL_QUERY,null);
//
//        Log.e("USER DATA",USER_DETAIL_QUERY);
//
//        try{
//
//            if (cursor.moveToFirst()){
//                do {
//
//                    CustomerModel cusmodel = new CustomerModel();
//                    cusmodel.Cname = cursor.getString(cursor.getColumnIndex(COL2));
//                    cusmodel.Cmobile = cursor.getString(cursor.getColumnIndex(COL3));
//                    cusmodel.Cemail = cursor.getString(cursor.getColumnIndex(COL4));
//                    customerModelList.add(cusmodel);
//
//                }while (cursor.moveToNext());
//            }
//        }catch (Exception e){
//
//        }
//        finally {
//            if (cursor != null && !cursor.isClosed()){
//                cursor.close();
//            }
//        }
//
//        return customerModelList;
//    }

}
