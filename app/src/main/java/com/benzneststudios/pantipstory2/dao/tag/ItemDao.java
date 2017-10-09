package com.benzneststudios.pantipstory2.dao.tag;

import com.benzneststudios.pantipstory2.dao.topic.TopicDao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 04-Oct-17.
 */

public class ItemDao {
    @SerializedName("related")
    @Expose
    private List<RelatedDao> related = null;
    @SerializedName("topic")
    @Expose
    private List<TopicDao> topic = null;
    @SerializedName("more")
    @Expose
    private String more;
    @SerializedName("last_id_current_page")
    @Expose
    private Integer lastIdCurrentPage;
    @SerializedName("tagIn")
    @Expose
    private List<Object> tagIn = null;
    @SerializedName("tagOut")
    @Expose
    private List<Object> tagOut = null;
    @SerializedName("logged_in")
    @Expose
    private Boolean loggedIn;

    public List<RelatedDao> getRelated() {
        return related;
    }

    public void setRelated(List<RelatedDao> related) {
        this.related = related;
    }

    public List<TopicDao> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicDao> topic) {
        this.topic = topic;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public Integer getLastIdCurrentPage() {
        return lastIdCurrentPage;
    }

    public void setLastIdCurrentPage(Integer lastIdCurrentPage) {
        this.lastIdCurrentPage = lastIdCurrentPage;
    }

    public List<Object> getTagIn() {
        return tagIn;
    }

    public void setTagIn(List<Object> tagIn) {
        this.tagIn = tagIn;
    }

    public List<Object> getTagOut() {
        return tagOut;
    }

    public void setTagOut(List<Object> tagOut) {
        this.tagOut = tagOut;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
