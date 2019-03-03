package com.benznestdeveloper.pantipstory.dao.topic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 29-Sep-17.
 */

public class MessageTopicListDao {
    @SerializedName("item")
    @Expose
    private ItemDao item;

    public ItemDao getItem() {
        return item;
    }

    public void setItem(ItemDao item) {
        this.item = item;
    }

}
