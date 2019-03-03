package com.benznestdeveloper.pantipstory.dao.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Oct-17.
 */

public class MessageTagTopicDao {
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
