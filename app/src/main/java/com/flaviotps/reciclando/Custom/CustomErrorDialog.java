package com.flaviotps.reciclando.Custom;


import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flaviotps.reciclando.Interfaces.ICustomDialogClickListener;
import com.flaviotps.reciclando.R;


public class CustomErrorDialog {

    ICustomDialogClickListener ICustomDialogClickListener;

    public CustomErrorDialog() {
    }

    public CustomErrorDialog(ICustomDialogClickListener customDialogClickListener) {
        this.ICustomDialogClickListener = customDialogClickListener;
    }

    public void showDialog(Activity activity, String msg) {


        final Dialog dialog = new Dialog(activity);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_erro);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICustomDialogClickListener.OnOK();
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}