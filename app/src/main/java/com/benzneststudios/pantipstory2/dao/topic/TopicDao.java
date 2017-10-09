package com.benzneststudios.pantipstory2.dao.topic;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benznest on 29-Sep-17.
 */

public class TopicDao implements Parcelable {

    @SerializedName("cover_img")
    @Expose
    private String coverImg;
    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("disp_topic")
    @Expose
    private String dispTopic;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("votes")
    @Expose
    private Integer votes;
    @SerializedName("utime")
    @Expose
    private String utime;
    @SerializedName("topic_type")
    @Expose
    private Integer topicType;
    @SerializedName("topic_class_icon")
    @Expose
    private String topicClassIcon;
    @SerializedName("abbr_title")
    @Expose
    private String abbrTitle;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("tags")
    @Expose
    private List<TagDao> tags = null;
    @SerializedName("cr_sr")
    @Expose
    private String crSr;

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDispTopic() {
        return dispTopic;
    }

    public void setDispTopic(String dispTopic) {
        this.dispTopic = dispTopic;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getTopicClassIcon() {
        return topicClassIcon;
    }

    public void setTopicClassIcon(String topicClassIcon) {
        this.topicClassIcon = topicClassIcon;
    }

    public String getAbbrTitle() {
        return abbrTitle;
    }

    public void setAbbrTitle(String abbrTitle) {
        this.abbrTitle = abbrTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<TagDao> getTags() {
        return tags;
    }

    public void setTags(List<TagDao> tags) {
        this.tags = tags;
    }

    public String getCrSr() {
        return crSr;
    }

    public void setCrSr(String crSr) {
        this.crSr = crSr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.coverImg);
        dest.writeValue(this.id);
        dest.writeString(this.dispTopic);
        dest.writeValue(this.comments);
        dest.writeValue(this.votes);
        dest.writeString(this.utime);
        dest.writeValue(this.topicType);
        dest.writeString(this.topicClassIcon);
        dest.writeString(this.abbrTitle);
        dest.writeString(this.author);
        dest.writeList(this.tags);
        dest.writeString(this.crSr);
    }

    public TopicDao() {
    }

    protected TopicDao(Parcel in) {
        this.coverImg = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dispTopic = in.readString();
        this.comments = (Integer) in.readValue(Integer.class.getClassLoader());
        this.votes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.utime = in.readString();
        this.topicType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.topicClassIcon = in.readString();
        this.abbrTitle = in.readString();
        this.author = in.readString();
        this.tags = new ArrayList<TagDao>();
        in.readList(this.tags, TagDao.class.getClassLoader());
        this.crSr = in.readString();
    }

    public static final Parcelable.Creator<TopicDao> CREATOR = new Parcelable.Creator<TopicDao>() {
        @Override
        public TopicDao createFromParcel(Parcel source) {
            return new TopicDao(source);
        }

        @Override
        public TopicDao[] newArray(int size) {
            return new TopicDao[size];
        }
    };
}
