package com.benznestdeveloper.pantipstory.dao.userTopic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 03-Oct-17.
 */

public class TagDao {
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("url")
    @Expose
    private String url;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
