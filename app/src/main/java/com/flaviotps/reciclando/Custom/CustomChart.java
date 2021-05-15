package com.flaviotps.reciclando.Custom;

import android.graphics.Color;

import com.flaviotps.reciclando.Adapter.ChartAdapter;
import com.flaviotps.reciclando.Data.ChartModel;
import com.flaviotps.reciclando.Utils.Constants;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class CustomChart {


    public static String empty = "";

    public static void CreateBarChart(ChartAdapter.BarViewHolder barViewHolder, ChartModel chartModel) {


        barViewHolder.Title.setText(chartModel.Title);
        barViewHolder.Chart.getDescription().setText(empty);  // Hide the description
        // chart.getAxisLeft().setDrawLabels(false);
        // chart.getAxisRight().setDrawLabels(false);
        barViewHolder.Chart.getXAxis().setDrawLabels(false);
        //chart.getLegend().setEnabled(false);

        barViewHolder.Chart.setPinchZoom(false);
        barViewHolder.Chart.setScaleEnabled(true);
        barViewHolder.Chart.setDrawBarShadow(false);
        barViewHolder.Chart.setDrawGridBackground(false);

        barViewHolder.Chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = barViewHolder.Chart.getAxisLeft();
        //leftAxis.setValueFormatter(new LargeValueFormatter("GB") );
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(32f);
        leftAxis.setAxisMinimum(0f);


        BarDataSet dataSet = new BarDataSet(chartModel.<BarEntry>getChartData(), chartModel.Label);
        dataSet.setColors(RandomColor());


        BarData data = new BarData(dataSet);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.GRAY);
        data.setBarWidth(0.3f); // set custom bar width
        barViewHolder.Chart.setData(data);
        barViewHolder.Chart.setFitBars(true); // make the x-axis fit exactly all bars
        barViewHolder.Chart.invalidate(); // refresh
    }


    private static List<Integer> RandomColor() {

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : Constants.COLORS_MAIN)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        return colors;

    }

    public static void CreatePieChart(ChartAdapter.PieViewHolder pieViewHolder, ChartModel chartModel) {


        // configure pie chart
        int colorBlack = Color.parseColor("#000000");
        pieViewHolder.Chart.setEntryLabelColor(colorBlack);
        pieViewHolder.Chart.setDrawCenterText(true);


        pieViewHolder.Title.setText(chartModel.Title);
        pieViewHolder.Chart.getDescription().setText(empty);

        // enable hole and configure
        pieViewHolder.Chart.setDrawHoleEnabled(true);
        pieViewHolder.Chart.setHoleRadius(7);
        pieViewHolder.Chart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        pieViewHolder.Chart.setRotationAngle(0);
        pieViewHolder.Chart.setRotationEnabled(true);


        PieDataSet set = new PieDataSet(chartModel.<PieEntry>getChartData(), empty);
        PieData data = new PieData(set);


        if (chartModel.Title.contains("(%)")) {
            pieViewHolder.Chart.setUsePercentValues(true);
            data.setValueFormatter(new PercentFormatter());
        }



        set.setColors(RandomColor());


        pieViewHolder.Chart.setData(data);

        // undo all highlights
        pieViewHolder.Chart.highlightValues(null);


        // customize legends
        Legend l = pieViewHolder.Chart.getLegend();

        //l.setXEntrySpace(7);
        // l.setYEntrySpace(5);
        //l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        //l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //l.setOrientation(Legend.LegendOrientation.VERTICAL);

        data.setValueTextSize(8f);
        data.setValueTextColor(Color.GRAY);
        set.setValueLinePart1OffsetPercentage(90.f);
        set.setValueLinePart1Length(1f);
        set.setValueLinePart2Length(.2f);
        set.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        set.setSliceSpace(2f);

        // update pie chart
        pieViewHolder.Chart.invalidate();


    }


}
