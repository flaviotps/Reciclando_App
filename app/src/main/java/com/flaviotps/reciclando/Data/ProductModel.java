package com.flaviotps.reciclando.Data;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    public String Cod;
    public String Title;
    public String Desc;
    public String Synonymous;
    public String Discard;

    public static List<ProductModel> filterByName(String name, List<ProductModel> productModelList) {

        if (name.isEmpty() || name.length() == 0) {
            return productModelList;
        }

        List<ProductModel> productModelArrayList = new ArrayList<>();

        for (ProductModel p : productModelList) {
            if (p.Title.toLowerCase().contains(name.toLowerCase()) || p.Synonymous.toLowerCase().contains(name.toLowerCase())) {
                productModelArrayList.add(p);
            }
        }

        return productModelArrayList;
    }
}
