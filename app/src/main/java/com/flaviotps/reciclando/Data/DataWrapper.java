package com.flaviotps.reciclando.Data;

import java.util.ArrayList;
import java.util.List;

public class DataWrapper {

    public List<ChartModel> graphList;
    public List<PageModel> pageList;
    public List<VideoModel> videoList;
    public List<LocationModel> locationList;
    public List<ProductModel> productList;
    public List<DiscardModel> discardList;
    public List<MaterialModel> materialList;



    public DataWrapper() {
        graphList = new ArrayList<>();
        pageList = new ArrayList<>();
        videoList = new ArrayList<>();
        locationList = new ArrayList<>();
        productList = new ArrayList<>();
        discardList = new ArrayList<>();
        materialList = new ArrayList<>();
    }

}