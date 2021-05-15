package com.flaviotps.reciclando.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviotps.reciclando.Custom.CustomVideoDetailsDialog;
import com.flaviotps.reciclando.Data.VideoModel;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {


    private List<VideoModel> videos;
    private Activity activity;

    public VideoAdapter(List<VideoModel> videos, Activity activity) {
        this.videos = videos;
        this.activity = activity;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mCustomView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_video, parent, false);

        VideoViewHolder videoViewHolder = new VideoViewHolder(mCustomView);

        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, final int position) {

        String url = VideoModel.getYoutubeThumbnailUrlFromVideoUrl(videos.get(position).Url);
        Glide.with(activity).load(url).into(holder.Thumbnail);
        holder.Title.setText(videos.get(position).Title);
        holder.Description.setText(videos.get(position).Description);
        holder.btnVideoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomVideoDetailsDialog.showDialog(activity, videos.get(position));
            }
        });

        holder.Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urlID = VideoModel.getYoutubeVideoIdFromUrl(videos.get(position).Url);
                if (urlID != null) {
                    Intent intent = YouTubeStandalonePlayer.createVideoIntent(activity, Constants.GOOGLE_YOUYUBE_API_KEY, urlID);
                    activity.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView Title;
        ImageView Thumbnail;
        TextView Description;
        ImageButton btnVideoDetails;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.videoTitle);
            Description = itemView.findViewById(R.id.videoDescription);
            Thumbnail = itemView.findViewById(R.id.videoThumbnail);
            btnVideoDetails = itemView.findViewById(R.id.btnVideoDetails);


        }
    }
}
