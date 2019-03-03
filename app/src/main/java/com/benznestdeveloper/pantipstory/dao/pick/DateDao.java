package com.benznestdeveloper.pantipstory.dao.pick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 01-Oct-17.
 */

public class DateDao {
    @SerializedName("$numberLong")
    @Expose
    private String numberLong;

    public String get$numberLong() {
        return numberLong;
    }

    public void set$numberLong(String numberLong) {
        this.numberLong = numberLong;
    }

}
