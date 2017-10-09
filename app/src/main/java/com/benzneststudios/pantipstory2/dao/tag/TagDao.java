package com.benzneststudios.pantipstory2.dao.tag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Oct-17.
 */

public class TagDao {
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("allow")
    @Expose
    private Boolean allow;

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

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }
}
