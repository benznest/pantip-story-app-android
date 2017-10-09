package com.benzneststudios.pantipstory2;

import android.content.Context;
import android.content.SharedPreferences;

import com.benzneststudios.pantipstory2.dao.TopicDataDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MyTopicHistory {

    private static final String SHARED_PREFERENCES_NAME = "PANTIP_STORY_TOPIC_HISTORY";
    private static SharedPreferences sp;

    private static String KEY_TOPIC_HISTORY = "KEY_TOPIC_HISTORY";

    private static void setContext(Context context) {
        if (context == null) {
            context = MyContextor.getInstance();
        }
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void add(TopicDataDao topic) {
        ArrayList<TopicDataDao> list = getTopicList();
        list.add(0,topic);
        setTopicList(list);
    }

    public static TopicDataDao get(int topicId){
        ArrayList<TopicDataDao> list = getTopicList();
        for (TopicDataDao t :list) {
            if(t.getTopic().getId() == topicId){
                return t;
            }
        }
        return null;
    }

    public static void removeAll(){
        setTopicList(new ArrayList<TopicDataDao>());
    }

    public static ArrayList<TopicDataDao> getTopicList() {
        setContext(MyContextor.getInstance());
        String json = sp.getString(KEY_TOPIC_HISTORY, "");

        ArrayList<TopicDataDao> list =
                new Gson().fromJson(json, new TypeToken<ArrayList<TopicDataDao>>() {
                }.getType());

        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }

    public static void setTopicList(ArrayList<TopicDataDao> list) {
        setContext(MyContextor.getInstance());
        String json = new Gson().toJson(list);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_TOPIC_HISTORY, json);
        editor.commit();
    }
}
