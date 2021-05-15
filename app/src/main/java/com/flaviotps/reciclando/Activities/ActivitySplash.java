package com.flaviotps.reciclando.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.flaviotps.reciclando.Custom.CustomConnectionDialog;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Interfaces.ICustomDialogClickListener;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.flaviotps.reciclando.Utils.Helper;

public class ActivitySplash extends AppCompatActivity implements ICustomDialogClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

       getSupportActionBar().hide();


        if (Helper.CheckConnection(this) == Constants.CONNECTION_OFFLINE) {
            CustomConnectionDialog alert = new CustomConnectionDialog(this);
            alert.showDialog(this, "Sem conexão. \n Os dados podem estar desatualizados.");
        } else {
            LoadData();
        }

    }


    private void LoadData() {
        DataRequestManager.getInstance(this).getData();
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void OnOK() {
        LoadData();
    }

    @Override
    public void OnCancel() {

    }


}



