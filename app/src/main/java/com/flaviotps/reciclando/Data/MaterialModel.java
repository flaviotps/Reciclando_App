package com.flaviotps.reciclando.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MaterialModel implements Serializable {

    public String Cod;
    public String PopularName;
    public String Name;
    public String Content;
    public String Type;
    public String Image;


    public static List<MaterialModel> filterByString(String type, List<MaterialModel> materialList) {
        List<MaterialModel> newMaterialModelsList = new ArrayList<>();

        for (MaterialModel materialModel : materialList) {
            if (materialModel.Type.toLowerCase().equals(type.toLowerCase())) {
                newMaterialModelsList.add(materialModel);
            }
        }

        return newMaterialModelsList;
    }

    public static List<MaterialModel> filterByName(String name, List<MaterialModel> materialList) {

        if (name.isEmpty() || name.length() == 0) {
            return materialList;
        }

        List<MaterialModel> newMaterialModelsList = new ArrayList<>();

        for (MaterialModel materialModel : materialList) {
            if (materialModel.PopularName.toLowerCase().contains(name.toLowerCase()) || materialModel.Name.toLowerCase().contains(name.toLowerCase())) {
                newMaterialModelsList.add(materialModel);
            }
        }

        return newMaterialModelsList;
    }

}


