package com.benzneststudios.pantipstory2.dao.userTopic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 03-Oct-17.
 */

public class TopicResultDao {
    @SerializedName("topic_id")
    @Expose
    private Integer topicId;
    @SerializedName("disp_topic")
    @Expose
    private String dispTopic;
    @SerializedName("icon_topic")
    @Expose
    private String iconTopic;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("votes")
    @Expose
    private Integer votes;
    @SerializedName("utime")
    @Expose
    private String utime;
    @SerializedName("abbr_title")
    @Expose
    private String abbrTitle;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("tags")
    @Expose
    private List<TagDao> tags = null;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getDispTopic() {
        return dispTopic;
    }

    public void setDispTopic(String dispTopic) {
        this.dispTopic = dispTopic;
    }

    public String getIconTopic() {
        return iconTopic;
    }

    public void setIconTopic(String iconTopic) {
        this.iconTopic = iconTopic;
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
}
