package com.flaviotps.reciclando.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviotps.reciclando.Data.VideoModel;
import com.flaviotps.reciclando.R;

public class CustomVideoDetailsDialog {

    private static Dialog dialog;


    public static void showDialog(Activity activity, VideoModel videoModel) {

        if (dialog == null) {
            dialog = new Dialog(activity);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_video);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_OK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getId() == R.id.btn_OK) {
                        dialog.dismiss();
                    }
                }
            });

        }


        ImageView VideoPreviewImg = (ImageView) dialog.findViewById(R.id.videoThumbnail);
        Glide.with(activity).load(VideoModel.getYoutubeThumbnailUrlFromVideoUrl(videoModel.Url)).into(VideoPreviewImg);

        TextView videoDetailsTxt = (TextView) dialog.findViewById(R.id.videoDetailsTxt);
        videoDetailsTxt.setText(videoModel.Description);
        dialog.show();


    }


}
