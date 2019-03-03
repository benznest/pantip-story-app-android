package com.benznestdeveloper.pantipstory.dao.userTopic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 03-Oct-17.
 */

public class MessageUserTopic {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("first_id")
    @Expose
    private Integer firstId;
    @SerializedName("last_id")
    @Expose
    private Integer lastId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<TopicResultDao> result = null;
    @SerializedName("max_page")
    @Expose
    private Integer maxPage;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TopicResultDao> getResult() {
        return result;
    }

    public void setResult(List<TopicResultDao> result) {
        this.result = result;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }
}
