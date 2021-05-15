package com.flaviotps.reciclando.Data;

import java.util.List;

public class PageModel {

    public String Cod;
    public String Number;
    public String Type;
    public String Title;

    public static PageModel getPageByCod(String cod, List<PageModel> pageModels) {

        for (PageModel p : pageModels) {
            if (p.Cod.equals(cod)) {
                return p;
            }
        }

        return null;
    }

    public int getIntPage() {
        return Integer.parseInt(Number);
    }

}
