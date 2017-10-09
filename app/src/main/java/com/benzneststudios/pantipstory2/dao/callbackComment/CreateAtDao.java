package com.benzneststudios.pantipstory2.dao.callbackComment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 03-Oct-17.
 */

public class CreateAtDao {
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
