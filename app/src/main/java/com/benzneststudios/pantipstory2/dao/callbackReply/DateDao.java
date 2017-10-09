package com.benzneststudios.pantipstory2.dao.callbackReply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 03-Oct-17.
 */

public class DateDao {
    @SerializedName("$numberLong")
    @Expose
    private String numberLong;

    public String getNumberLong() {
        return numberLong;
    }

    public void setNumberLong(String numberLong) {
        this.numberLong = numberLong;
    }
}
