package com.flaviotps.reciclando.Utils;

import android.graphics.Color;

public class Constants {

    public static final int MAIN_FRAGMENT_MAIN = 0;
    public static final int MAIN_FRAGMENT_MATERIAL = 1;
    public static final int MAIN_FRAGMENT_PRODUCTS = 2;

    public static final String MATERIAL_TYPE_PAPER = "Papel";
    public static final String MATERIAL_TYPE_PLASTIC = "Plastico";
    public static final String MATERIAL_TYPE_GLASS = "Vidro";
    public static final String MATERIAL_TYPE_METAL = "Metal";
    public static final String MATERIAL_TYPE_ORGANIC = "Organico";
    public static final String MATERIAL_TYPE_BATTERY = "Bateria";
    public static final String MATERIAL_TYPE_LIGHT_BULB = "LAMPADA";
    public static final String MATERIAL_TYPE_ELETRONIC = "Eletronico";
    public static final String MATERIAL_TYPE_MIXED = "Misturado";


    public static final int BUTTONS_MATERIAL_MENU_COUNT = 8;
    public static final int BUTTONS_MAIN_MENU_COUNT = 6;

    public static final int PERMISSION_RESULT_CODE_OK = 10;
    public static final String FONT_MENU = "fonts/Ecolier.ttf";

    public static final String CHART_TYPE_MULTI_BAR = "MultiBarChart";
    public static final String CHART_TYPE_BAR = "BarChart";
    public static final String CHART_TYPE_PIE = "PieChart";


    public static final int CONNECTION_OFFLINE = 0;
    public static final int CONNECTION_TYPE_MOBILE = 1;
    public static final int CONNECTION_TYPE_WIFI = 2;
    public static final int CONNECTION_TYPE_OTHER = 3;


    public static final String REF_GRAPH = "graph";
    public static final String REF_VIDEO = "video";
    public static final String REF_PAGE = "page";
    public static final String REF_PRODUCT = "product";
    public static final String REF_DISCARD = "discard";
    public static final String REF_MATERIAL = "material";
    public static final String REF_APP = "App";

    public static final String BUNDLE_ARG_MATERIAL_TYPE = "material_type";
    public static final String BUNDLE_ARG_PAGE_ID = "page_id";
    public static final String BUNDLE_ARG_LAST_POSITION_LNG = "last_position_lat";
    public static final String BUNDLE_ARG_LAST_POSITION_LAT = "last_position_lng";
    public static final String BUNDLE_ARG_MATERIAL = "BUNDLE_MATERIAL";

    public static final int[] COLORS_MAIN = {

            Color.rgb(192, 255, 140),
            Color.rgb(255, 140, 157),
            Color.rgb(140, 234, 255),
            Color.rgb(255, 247, 140),
            Color.rgb(255, 208, 140)
    };

    public static final String GOOGLE_PLACES_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    public static final String GOOGLE_PLACES_SERVER_KEY = "AIzaSyAeXmRQ5QgTjVzMa45XdguhBAqY49ORzFY";
    public static final String GOOGLE_YOUYUBE_API_KEY = "AIzaSyAVE6hCUgw4VtnTqKoNHb3C-m-9FLKBpS4";


}
