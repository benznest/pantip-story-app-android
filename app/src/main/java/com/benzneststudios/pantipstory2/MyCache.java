package com.benzneststudios.pantipstory2;

import android.content.Context;
import android.content.SharedPreferences;

import com.benzneststudios.pantipstory2.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by benznest on 02-Oct-17.
 */

public class MyCache {
    private static final String SHARED_PREFERENCES_NAME = "PANTIP_STORY_USER";
    private static SharedPreferences sp;

    public static String KEY_USER = "KEY_USER";
    public static String KEY_THEME = "KEY_THEME";
    public static String KEY_LAST_VERSION = "KEY_LAST_VERSION";

    private static void setContext(Context context) {
        if (context == null) {
            context = MyContextor.getInstance();
        }
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static UserDao getUser(Context context) {
        setContext(context);
        String json = sp.getString(KEY_USER, "");

        UserDao user =
                new Gson().fromJson(json, new TypeToken<UserDao>() {
                }.getType());

        return user;
    }

    public static void setUser(Context context, UserDao userDao) {
        setContext(context);
        String json = new Gson().toJson(userDao);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_USER, json);
        editor.commit();
    }

    public static boolean isLogin() {
        return getUser(MyContextor.getInstance()) != null;
    }

    public static int getTheme(Context context) {
        setContext(context);
        return sp.getInt(KEY_THEME, 0);
    }

    public static void setTheme(Context context, int theme_index) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_THEME, theme_index);
        editor.commit();
    }

    public static int getLastVersion(Context context) {
        setContext(context);
        return sp.getInt(KEY_LAST_VERSION, 0);
    }

    public static void setLastVersion(Context context, int version) {
        setContext(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_LAST_VERSION, version);
        editor.commit();
    }

}
