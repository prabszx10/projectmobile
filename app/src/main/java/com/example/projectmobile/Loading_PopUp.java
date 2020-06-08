package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Loading_PopUp extends AppCompatActivity {

    Activity activity;
    AlertDialog dialog;

    Loading_PopUp(Activity myActivity){
        activity=myActivity;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);

        LayoutInflater inflater= activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_loading__pop_up, null));

        if(builder.setCancelable(true).equals(builder.setCancelable(true))){

        }

        dialog = builder.create();
        dialog.show();

    }

    void dissmissDialog(){
        dialog.dismiss();
    }
}
