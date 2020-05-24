package com.example.tellycaller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelData extends AppCompatActivity {

    String exceldata ="";
    TextView tv1,tv2,tv3,tv4;
    Button bt;
    Intent impin;
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_data);

        bt = findViewById(R.id.impor);

        CopyAssets();
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                impin = new Intent(Intent.ACTION_GET_CONTENT);
//                impin.setType("*/*");
//                startActivityForResult(impin, 10);
//
//            }
//        });
    }

    private void CopyAssets() {

        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("Files");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for(String filename : files) {
            System.out.println("File name => "+filename);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("Files/"+filename);   // if files resides inside the "Files" directory itself
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() +"/" + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(Exception e) {
                Log.e("tag", e.getMessage());
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode){
//
//            case 10:
//                if (resultCode==RESULT_OK){
//                    path = data.getData().getPath();
//             //       Toast.makeText(this, "Here" +path, Toast.LENGTH_SHORT).show();
//                    try {
//
//                        AssetManager am = getAssets();
//                        InputStream is = am.open(path);
//                        Workbook wb = Workbook.getWorkbook(is);
//                        Sheet s = wb.getSheet(0);
//                        int row = s.getRows();
//                        int coloum = s.getColumns();
//
//                        for (int i=0; i<row; i++){
//
//                            for (int j=0; j<coloum; j++){
//
//                                Cell z = s.getCell(j,i);
//                                exceldata = exceldata+z.getContents();
//                                displayData(exceldata);
//                                Toast.makeText(this, "DAta"+exceldata, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//
//
//                    }catch (Exception e){
//
//                    }
//
//                }
//                break;
//        }
//    }




}