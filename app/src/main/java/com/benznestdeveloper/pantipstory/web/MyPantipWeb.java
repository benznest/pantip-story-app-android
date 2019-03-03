package com.benznestdeveloper.pantipstory.web;

import android.support.v4.content.ContextCompat;

import com.benznestdeveloper.pantipstory.MyContextor;
import com.benznestdeveloper.pantipstory.MyTheme;
import com.benznestdeveloper.pantipstory.R;

/**
 * Created by benznest on 01-Oct-17.
 */

public class MyPantipWeb {

    public static String trimBody(String str) {
        return str.replace("\"\"", "");
    }

    public static String getColor(int resAttr) {
        int attributeResourceId = MyTheme.getResourceIdOnTheme(resAttr);
        return "#" + Integer.toHexString(ContextCompat.getColor
                (MyContextor.getInstance(), attributeResourceId) & 0x00ffffff);
    }

    public static final String jsEnableSpoil = "<script>" +
            "$(document).on('click', '.spoil-btn',function(){" +
            "$(this).html('[Spoil] คลิกเพื่อดูข้อความที่ซ่อนไว้').toggleClass('spoil_hide').next().toggle();" +
            "$('.spoil_hide').html('[Spoil] คลิกเพื่อซ่อนข้อความ');" +
            "});" +
            "</script>";

    public static final String jsImportJquery = "<script type=\"text/javascript\" src=\"file:///android_asset/web/jquery.js\"></script>";

    public static final String cssSpoil = "<style>.spoil-style {" +
            "border: dashed 1px #ddd;" +
            "padding: 10px 0px 10px 20px;" +
            "margin: 10px 0px;" +
            "display: block;" +
            "}</style>";


    public static String getCssPantipMain(Boolean isOwner) {
           String cssPantipMainOwner = "<style>" +
                "img{display: inline;height: auto;max-width: 100%;}" +
                "body{background-color: " + getColor( R.attr.colorOwner) + ";color:white}" +
                "a:link {color: #f1c40f;}" +
                "a:visited { color: #f1c40f;}" +
                "</style>";

           String cssPantipMain = "<style>" +
                "img{display: inline;height: auto;max-width: 100%;}" +
                "body{background-color: "+getColor(R.attr.colorPrimary)+";color:white}" +
                "a:link {color: #f1c40f;}" +
                "a:visited { color: #f1c40f;}" +
                "</style>";

        if (isOwner != null && isOwner) {
            return cssPantipMainOwner;
        } else {
            return cssPantipMain;
        }
    }
}
