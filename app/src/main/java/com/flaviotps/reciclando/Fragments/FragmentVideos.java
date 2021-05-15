package com.flaviotps.reciclando.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flaviotps.reciclando.Adapter.VideoAdapter;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Data.VideoModel;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentVideos extends Fragment implements View.OnClickListener {

    private VideoAdapter genericVideoAdapter;
    private View fragmentView;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private int PageID;

    public static FragmentVideos newInstance(int page_id, Activity activity) {
        FragmentVideos f = new FragmentVideos();
        Bundle args = new Bundle();
        args.putInt(Constants.BUNDLE_ARG_PAGE_ID, page_id);
        f.setArguments(args);
        f.setGenericChartAdapter(new VideoAdapter(VideoModel.getVideosByPage(page_id, DataRequestManager.data.videoList), activity));
        return f;
    }

    public void setGenericChartAdapter(VideoAdapter videoAdapter) {
        this.genericVideoAdapter = videoAdapter;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_videos, container, false);

        Bundle args = getArguments();
        PageID = args.getInt(Constants.BUNDLE_ARG_PAGE_ID, 0);

        LoadVideos(PageID);

        return fragmentView;
    }

    @Override
    public void onClick(View view) {


    }

    private void LoadVideos(int PageID) {

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = fragmentView.findViewById(R.id.VideosRecycler);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(genericVideoAdapter);

    }
}


