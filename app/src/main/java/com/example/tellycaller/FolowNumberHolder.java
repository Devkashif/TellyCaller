package com.example.tellycaller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FolowNumberHolder extends RecyclerView.ViewHolder {

    View fview;

    public FolowNumberHolder(@NonNull View itemView) {
        super(itemView);
        fview = itemView;
    }

    public void setName(String Name){

        TextView txv1 = fview.findViewById(R.id.cnames);
        txv1.setText(Name);
    }

    public void setMobile_num(String Mobile_num,String Status){
        TextView txv2 = fview.findViewById(R.id.cmobils);
        txv2.setText(Mobile_num +"\n"+ Status);
    }

    public void setRemarks(String Remarks){
        TextView txv3 = fview.findViewById(R.id.crmrk);
        txv3.setText(Remarks);
    }
    public void setAppointment_Dt_Tm(String Appointment_Dt_Tm){
        TextView txv4 = fview.findViewById(R.id.cupdates);
        txv4.setText(Appointment_Dt_Tm);
    }
}

