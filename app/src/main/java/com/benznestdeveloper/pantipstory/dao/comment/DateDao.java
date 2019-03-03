package com.benznestdeveloper.pantipstory.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
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
