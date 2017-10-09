package com.benzneststudios.pantipstory2.dao.callbackReply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 03-Oct-17.
 */

public class CreateAtDao {
    @SerializedName("$date")
    @Expose
    private DateDao date;

    public DateDao getDate() {
        return date;
    }

    public void setDate(DateDao date) {
        this.date = date;
    }
}
