package com.benznestdeveloper.pantipstory.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 30-Sep-17.
 */

public class PagingDao {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("limit_reply")
    @Expose
    private Integer limitReply;
    @SerializedName("limit_hybrid_reply")
    @Expose
    private Integer limitHybridReply;
    @SerializedName("defalut")
    @Expose
    private Integer defalut;
    @SerializedName("topic_id")
    @Expose
    private Integer topicId;
    @SerializedName("param")
    @Expose
    private String param;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("st")
    @Expose
    private String st;
    @SerializedName("ed")
    @Expose
    private EdDao ed;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("expand")
    @Expose
    private List<Object> expand = null;
    @SerializedName("topic_status")
    @Expose
    private Integer topicStatus;
    @SerializedName("max_comments")
    @Expose
    private Integer maxComments;
    @SerializedName("chk_best_answer")
    @Expose
    private Integer chkBestAnswer;
    @SerializedName("author_mid")
    @Expose
    private Integer authorMid;
    @SerializedName("sess_mid")
    @Expose
    private Object sessMid;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimitReply() {
        return limitReply;
    }

    public void setLimitReply(Integer limitReply) {
        this.limitReply = limitReply;
    }

    public Integer getLimitHybridReply() {
        return limitHybridReply;
    }

    public void setLimitHybridReply(Integer limitHybridReply) {
        this.limitHybridReply = limitHybridReply;
    }

    public Integer getDefalut() {
        return defalut;
    }

    public void setDefalut(Integer defalut) {
        this.defalut = defalut;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public EdDao getEd() {
        return ed;
    }

    public void setEd(EdDao ed) {
        this.ed = ed;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<Object> getExpand() {
        return expand;
    }

    public void setExpand(List<Object> expand) {
        this.expand = expand;
    }

    public Integer getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(Integer topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Integer getMaxComments() {
        return maxComments;
    }

    public void setMaxComments(Integer maxComments) {
        this.maxComments = maxComments;
    }

    public Integer getChkBestAnswer() {
        return chkBestAnswer;
    }

    public void setChkBestAnswer(Integer chkBestAnswer) {
        this.chkBestAnswer = chkBestAnswer;
    }

    public Integer getAuthorMid() {
        return authorMid;
    }

    public void setAuthorMid(Integer authorMid) {
        this.authorMid = authorMid;
    }

    public Object getSessMid() {
        return sessMid;
    }

    public void setSessMid(Object sessMid) {
        this.sessMid = sessMid;
    }
}
