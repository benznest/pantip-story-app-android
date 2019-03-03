package com.benznestdeveloper.pantipstory;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by benznest on 02-Oct-17.
 */

public class MySetting {
    private static final String SHARED_PREFERENCES_NAME = "PANTIP_STORY_SETTING";
    private static SharedPreferences sp;

    public static String KEY_ENABLE_EMOTION_VIEW = "KEY_ENABLE_EMOTION_VIEW";
    public static String KEY_DISPLAY_DEFAULT_ROOM = "KEY_DISPLAY_DEFAULT_ROOM";
    public static String KEY_ENABLE_DISPLAY_ROOM_USAGE_COUNT = "KEY_ENABLE_DISPLAY_ROOM_USAGE_COUNT";

    private static void setContext(Context context) {
        if (context == null) {
            context = MyContextor.getInstance();
        }
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isEnableEmotionView(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_ENABLE_EMOTION_VIEW, true);
    }

    public static void setEnableEmotionView(Context context, boolean is) {
        setContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_ENABLE_EMOTION_VIEW, is);
        editor.commit();
    }

    public static int getDisplayDefaultRoom(Context context) {
        setContext(context);
        return sp.getInt(KEY_DISPLAY_DEFAULT_ROOM, 0);
    }

    public static void setDisplayDefaultRoom(Context context, int is) {
        setContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_DISPLAY_DEFAULT_ROOM, is);
        editor.commit();
    }

    public static boolean isEnableDisplayRoomUsageCount(Context context) {
        setContext(context);
        return sp.getBoolean(KEY_ENABLE_DISPLAY_ROOM_USAGE_COUNT, true);
    }

    public static void setEnableDisplayRoomUsageCount(Context context, boolean is) {
        setContext(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_ENABLE_DISPLAY_ROOM_USAGE_COUNT, is);
        editor.commit();
    }


}
