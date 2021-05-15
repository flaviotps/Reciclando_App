package com.flaviotps.reciclando.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flaviotps.reciclando.Custom.CustomChart;
import com.flaviotps.reciclando.Data.ChartModel;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChartModel> charts;

    public ChartAdapter(List<ChartModel> charts) {

        this.charts = charts;
    }

    public List<ChartModel> getCharts() {
        return charts;
    }

    @Override
    public int getItemViewType(int position) {

        switch (charts.get(position).Type) {

            case Constants.CHART_TYPE_BAR:
                return R.layout.include_bar_chart;

            case Constants.CHART_TYPE_MULTI_BAR:
                return R.layout.include_bar_chart;


            case Constants.CHART_TYPE_PIE:
                return R.layout.include_pie_chart;
        }

        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;

        switch (viewType) {
            case R.layout.include_bar_chart:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_bar_chart, parent, false);
                viewHolder = new BarViewHolder(view);
                break;

            case R.layout.include_pie_chart:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_pie_chart, parent, false);
                viewHolder = new PieViewHolder(view);
                break;


        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BarViewHolder) {
            // bind BarData
            ChartModel chartModel = charts.get(position);
            CustomChart.CreateBarChart((BarViewHolder) holder, chartModel);

        }

        if (holder instanceof PieViewHolder) {
            // bind BarData
            ChartModel chartModel = charts.get(position);
            CustomChart.CreatePieChart((PieViewHolder) holder, chartModel);

        }

    }

    @Override
    public int getItemCount() {
        return charts.size();
    }

    public class BarViewHolder extends RecyclerView.ViewHolder {


        public TextView Title;
        public BarChart Chart;

        public BarViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.BarChartTitle);
            Chart = itemView.findViewById(R.id.BarChart);
        }
    }

    public class PieViewHolder extends RecyclerView.ViewHolder {


        public TextView Title;
        public PieChart Chart;

        public PieViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.PieChartTitle);
            Chart = itemView.findViewById(R.id.PieChart);
        }
    }
}
