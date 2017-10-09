package com.benzneststudios.pantipstory2.dao.pick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 01-Oct-17.
 */

public class CreatedTimeDao {
    @SerializedName("$date")
    @Expose
    private DateDao date;

    public DateDao get$date() {
        return date;
    }

    public void set$date(DateDao date) {
        this.date = date;
    }
}
