package com.example.tellycaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TintableImageSourceView;
import static android.Manifest.permission.CALL_PHONE;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CallingList extends AppCompatActivity {

    ListView listView;
    TextView connum;
    ImageView calllogo;
    String[] listitem;
    //Integer[] img = {R.drawable.cal24dp,R.drawable.cal24dp,R.drawable.cal24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        listView = findViewById(R.id.contactlist);
        connum = findViewById(R.id.num);
        calllogo = findViewById(R.id.cal);

       // listitem = getResources().getStringArray(R.array.contactnumberlist);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,listitem);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent callintent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" +listitem[i]));

                if (ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(callintent);
                    finish();
                }else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{CALL_PHONE},1);
                    }
                }

//
//                switch (i){
//
//                    case 0:
//
//                        break;
//                    case 1:
//                        Toast.makeText(CallingList.this, "Calling2", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(CallingList.this, "Calling3", Toast.LENGTH_SHORT).show();
//                        break;
//                }
            }
        });

    }
}
