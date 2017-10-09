package com.benzneststudios.pantipstory2.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 30-Sep-17.
 */

public class MessageCommentListDao {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("check_pinit")
    @Expose
    private String checkPinit;
    @SerializedName("paging")
    @Expose
    private PagingDao paging;
    @SerializedName("comments")
    @Expose
    private List<CommentDao> comments = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCheckPinit() {
        return checkPinit;
    }

    public void setCheckPinit(String checkPinit) {
        this.checkPinit = checkPinit;
    }

    public PagingDao getPaging() {
        return paging;
    }

    public void setPaging(PagingDao paging) {
        this.paging = paging;
    }

    public List<CommentDao> getComments() {
        return comments;
    }

    public void setComments(List<CommentDao> comments) {
        this.comments = comments;
    }
}
