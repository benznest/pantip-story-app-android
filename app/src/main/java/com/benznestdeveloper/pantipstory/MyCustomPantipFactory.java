package com.benznestdeveloper.pantipstory;

import com.benznestdeveloper.pantipstory.dao.pick.MyConvertStringToListStringClassTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by benznest on 01-Oct-17.
 */

public class MyCustomPantipFactory {
    public static Converter.Factory getPantipPickFactory() {
        Type myClassListType = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(myClassListType, new MyConvertStringToListStringClassTypeAdapter())
                .create();

        Converter.Factory factory = GsonConverterFactory.create(gson);
        return factory;
    }
}
