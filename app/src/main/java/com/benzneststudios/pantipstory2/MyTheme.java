package com.benzneststudios.pantipstory2;

import android.content.res.TypedArray;

import com.benzneststudios.pantipstory2.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by benznest on 05-Oct-17.
 */

public class MyTheme {
    private static ArrayList<MyThemeObject> listTheme;

    public static int getResourceIdOnTheme(int resAttr) {
        TypedArray a = MainActivity.getMainActivity().getTheme().obtainStyledAttributes(
                MyTheme.getCurrentTheme().getTheme(), new int[]{resAttr});
        int attributeResourceId = a.getResourceId(0, 0);
        a.recycle();
        return attributeResourceId;
    }

    public static void init() {
        listTheme = new ArrayList<>();
        listTheme.add(new MyThemeObject(
                R.style.AppThemePantip,
                "Pantip",
                R.color.colorPantip, R.color.colorPantipDark, R.color.colorPantipAccent, R.color.colorPantipTextPrimary,
                false));

        listTheme.add(new MyThemeObject(
                R.style.AppThemePantipCommemorate,
                "Pantip Commemorate",
                R.color.colorPantipCom, R.color.colorPantipComDark, R.color.colorPantipComAccent, R.color.colorPantipComTextPrimary,
                true));

//            listTheme.add(new MyThemeObject(
//                R.style.AppThemePantipLight,
//                "Pantip Light",
//                R.color.colorPantipLight, R.color.colorPantipLightDark, R.color.colorPantipLightAccent, R.color.colorPantipLightTextPrimary,
//                true));
    }

    public static ArrayList<MyThemeObject> getTheme() {
        if (listTheme == null) {
            init();
        }
        return listTheme;
    }

    public static String[] getThemeName() {
        listTheme = getTheme();
        String[] str = new String[listTheme.size()];
        for (int i = 0; i < listTheme.size(); i++) {
            str[i] = listTheme.get(i).getName();
        }
        return str;
    }

    public static MyThemeObject getCurrentTheme() {
        int index = MyCache.getTheme(MyContextor.getInstance());
        return listTheme.get(index);
    }

    public static class MyThemeObject {
        private int theme;
        private String name;
        private int colorPrimary;
        private int colorSecondary;
        private int colorIndicator;
        private int colorText;

        public MyThemeObject(int theme, String name, int colorPrimary, int colorSecondary, int colorIndicator, int colorText, boolean isProOnly) {
            this.theme = theme;
            this.name = name;
            this.colorPrimary = colorPrimary;
            this.colorSecondary = colorSecondary;
            this.colorIndicator = colorIndicator;
            this.colorText = colorText;
        }

        public int getTheme() {
            return theme;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColorPrimary() {
            return colorPrimary;
        }

        public void setColorPrimary(int colorPrimary) {
            this.colorPrimary = colorPrimary;
        }

        public int getColorSecondary() {
            return colorSecondary;
        }

        public void setColorSecondary(int colorSecondary) {
            this.colorSecondary = colorSecondary;
        }

        public int getColorIndicator() {
            return colorIndicator;
        }

        public void setColorIndicator(int colorIndicator) {
            this.colorIndicator = colorIndicator;
        }

        public int getColorText() {
            return colorText;
        }

        public void setColorText(int colorText) {
            this.colorText = colorText;
        }
    }
}
