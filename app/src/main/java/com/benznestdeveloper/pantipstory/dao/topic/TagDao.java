package com.benznestdeveloper.pantipstory.dao.topic;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 29-Sep-17.
 */

public class TagDao implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeString(this.url);
        dest.writeValue(this.allow);
    }

    public TagDao() {
    }

    protected TagDao(Parcel in) {
        this.tag = in.readString();
        this.url = in.readString();
        this.allow = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TagDao> CREATOR = new Parcelable.Creator<TagDao>() {
        @Override
        public TagDao createFromParcel(Parcel source) {
            return new TagDao(source);
        }

        @Override
        public TagDao[] newArray(int size) {
            return new TagDao[size];
        }
    };
}
