package com.benznestdeveloper.pantipstory.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class DateActiveDao {
    @SerializedName("$date")
    @Expose
    private DateDao date;

    public DateDao get$date() {
        return date;
    }

    public void set$date(DateDao $date) {
        this.date = $date;
    }
}
