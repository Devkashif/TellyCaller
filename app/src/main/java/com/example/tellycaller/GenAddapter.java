package com.example.tellycaller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GenAddapter extends RecyclerView.ViewHolder {

    View mview;

    public GenAddapter(@NonNull View itemView) {
        super(itemView);
        mview = itemView;
    }


    public void setUserName(String Name) {

        TextView cname = mview.findViewById(R.id.cname);
        cname.setText(Name);
    }

    public void setUserMobile(String Mobile_num) {

        TextView cnumber = mview.findViewById(R.id.cmobil);
        cnumber.setText(Mobile_num);
    }



////        ArrayList cusMobile;
////        ArrayList cusName;
//
//
//        //Cursor mCursor;
//        Context context;
//
//        public GenAddapter(Context context) {
//
//            this.context = context;
//        }
//
//        //        public GenAddapter(Cursor cursor, Context context) {
////            this.mCursor = cursor;
////            this.context = context;
////
////        }
//
//        @NonNull
//        @Override
//        public GenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gitemlayout,parent,false);
//
//            GenHolder genHolder = new GenHolder(v);
//            v.setTag(genHolder);
//            return (genHolder);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull final GenHolder holder, final int position) {
//
//            holder.sr.setText((position+1)+"");
//            holder.name.setText(datalist.get(position).Cname);
//
//            holder.mobile.setText(datalist.get(position).Cmobile);
//            holder.mobile.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String st = datalist.get(position).Cmobile;
//
//                    if (ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
//                        Intent calin = new Intent(Intent.ACTION_CALL);
//                        calin.setData(Uri.parse("tel:" + st));
//                        startActivity(calin);
//                    }else {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                            requestPermissions(new String[]{CALL_PHONE},1);
//                        }
//                    }
//                }
//            });
//
//            holder.updt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ShowUpdateDialogBox();
//                    Uname = holder.name.getText().toString();
//                    Unum =  holder.mobile.getText().toString();
//
//                }
//            });
//
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return datalist.size();
//        }
//
//
////        public void swapcursor(Cursor newCursor){
////            if (mCursor != null){
////                mCursor.close();
////            }
////            mCursor = newCursor;
////            if (newCursor != null){
////                notifyDataSetChanged();
////            }
////        }
//
//        public class GenHolder extends RecyclerView.ViewHolder{
//
//            TextView sr,name,mobile;
//            Button updt;
//
//            public GenHolder(@NonNull View itemView) {
//                super(itemView);
//
//                sr = itemView.findViewById(R.id.snum);
//                name = itemView.findViewById(R.id.cname);
//                mobile = itemView.findViewById(R.id.cmobil);
//                updt = itemView.findViewById(R.id.cupdate);
//            }
//
//        }

}
