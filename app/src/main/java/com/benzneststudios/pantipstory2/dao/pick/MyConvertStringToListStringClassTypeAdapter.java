package com.benzneststudios.pantipstory2.dao.pick;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 01-Oct-17.
 */

public class MyConvertStringToListStringClassTypeAdapter implements JsonDeserializer<List<String>> {
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
        List<String> vals = new ArrayList<>();
        if (json.isJsonArray()) {
            for (JsonElement e : json.getAsJsonArray()) {
                vals.add((String) ctx.deserialize(e, String.class));
            }
        } else {

        }

        return vals;
    }
}
