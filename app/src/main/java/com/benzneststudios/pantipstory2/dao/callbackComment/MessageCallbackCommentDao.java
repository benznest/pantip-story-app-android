package com.benzneststudios.pantipstory2.dao.callbackComment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 03-Oct-17.
 */

public class MessageCallbackCommentDao {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comment_no")
    @Expose
    private Integer commentNo;
    @SerializedName("comment_id")
    @Expose
    private Integer commentId;
    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("createAt")
    @Expose
    private CreateAtDao createAt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public CreateAtDao getCreateAt() {
        return createAt;
    }

    public void setCreateAt(CreateAtDao createAt) {
        this.createAt = createAt;
    }

}
