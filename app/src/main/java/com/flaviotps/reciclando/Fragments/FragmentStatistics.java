package com.flaviotps.reciclando.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flaviotps.reciclando.Adapter.ChartAdapter;
import com.flaviotps.reciclando.Data.ChartModel;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentStatistics extends Fragment implements View.OnClickListener {

    private View fragmentView;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private int PageID;
    private ChartAdapter chartAdapter;

    public static FragmentStatistics newInstance(int page_id) {
        FragmentStatistics f = new FragmentStatistics();
        Bundle args = new Bundle();
        args.putInt(Constants.BUNDLE_ARG_PAGE_ID, page_id);
        f.setArguments(args);
        f.setChartAdapter(new ChartAdapter(ChartModel.getChartsByPage(page_id, DataRequestManager.data.graphList)));
        return f;
    }

    public ChartAdapter getChartAdapter() {
        return chartAdapter;
    }

    public void setChartAdapter(ChartAdapter chartAdapter) {
        this.chartAdapter = chartAdapter;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_statistics, container, false);

        Bundle args = getArguments();
        PageID = args.getInt(Constants.BUNDLE_ARG_PAGE_ID, 0);
        LoadGraphs(PageID);

        return fragmentView;
    }

    @Override
    public void onClick(View view) {


    }

    private void LoadGraphs(int PageID) {

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = fragmentView.findViewById(R.id.Chart1Recycler);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(chartAdapter);

    }
}


