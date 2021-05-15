package com.flaviotps.reciclando.Data;

import android.graphics.Color;

import com.flaviotps.reciclando.Utils.Constants;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ChartModel {

    public String Cod;
    public String Data;
    public String Label;
    public String Type;
    public String Title;
    public String Page;
    public int POSITION_X = 0;
    public int POSITION_VALUE = 1;
    public int POSITION_COLOR = 2;
    private PageModel TabPage = new PageModel();

    public static List<ChartModel> getChartsByPage(int PageID, List<ChartModel> charts) {
        List<ChartModel> newCharts = new ArrayList<>();

        for (int i = 0; i < charts.size(); i++) {
            if (charts.get(i).getTabPage().getIntPage() == PageID) {
                newCharts.add(charts.get(i));
            }
        }


        return newCharts;
    }

    public static List<PageModel> GetUniquePages(List<ChartModel> charts) {

        if (charts == null) {
            return null;
        }

        List<PageModel> tabPages = new ArrayList<>();

        for (int i = 0; i < charts.size(); i++) {
            boolean found = false;
            for (int j = 0; j < tabPages.size(); j++) {
                if (tabPages.get(j).Cod.equals(charts.get(i).Page)) {
                    found = true;
                }
            }

            if (!found) {
                tabPages.add(charts.get(i).getTabPage());
            }
        }


        return tabPages;
    }

    public PageModel getTabPage() {
        return TabPage;
    }



    public static String getPieChartLabel(String Label, int pos) {
        String[] splitedLabel = Label.split(",");

        if (splitedLabel[pos - 1] != null) {
            return splitedLabel[pos - 1];
        } else {
            return "";
        }
    }

    public static Float StringToFloat(String s) {
        s = s.replace(".", "");
        return Float.valueOf(s);

    }

    public String getChartType() {
        return Type;
    }

    public int[] getColor() {

        String[] DataArray = Data.trim().replace(" ", "").split(";");
        List<Integer> colors = new ArrayList<>();

        for (int i = 0; i < DataArray.length; i++) {
            switch (Type) {

                case Constants.CHART_TYPE_BAR:
                    String[] info = DataArray[i].split(",");
                    int color = Color.parseColor(String.valueOf(info[POSITION_COLOR]).trim());
                    colors.add(color);
                    break;
            }
        }

        int[] colorArray = new int[colors.size()];

        for (int i = 0; i < colorArray.length; i++) {
            colorArray[i] = colors.get(i).intValue();
        }
        return colorArray;
    }

    public <T> List<T> getChartData() {

        List<T> entries = new ArrayList<T>();
        String[] DataArray = Data.trim().replace(" ", "").split(";");

        for (int i = 0; i < DataArray.length; i++) {
            switch (Type) {

                case Constants.CHART_TYPE_BAR:
                    String[] BarInfo = DataArray[i].split(",");
                    float BarPos = Float.valueOf(BarInfo[POSITION_X]);
                    float BarValue = StringToFloat(BarInfo[POSITION_VALUE]);
                    entries.add((T) new BarEntry(BarPos, BarValue));
                    break;


                case Constants.CHART_TYPE_PIE:
                    String[] PieInfo = DataArray[i].split(",");
                    float PieTitlePos = Float.valueOf(PieInfo[POSITION_X]);
                    float PíeValue = StringToFloat(PieInfo[POSITION_VALUE]);
                    entries.add((T) new PieEntry(PíeValue, getPieChartLabel(Label, (int) PieTitlePos)));

                    break;


            }
        }
        return entries;
    }

    public void setTabPage(PageModel tabPage) {
        TabPage = tabPage;
    }
}
