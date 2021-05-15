package com.flaviotps.reciclando.Data;

import java.util.List;

public class DiscardModel {

    public String Cod;
    public String Title;
    public String Desc;

    public static String getDiscard(String code, List<DiscardModel> discardList) {
        for (DiscardModel discard:discardList) {
            if(discard.Cod.equals(code))
                return discard.Title;
        }

        return "";
    }
}
