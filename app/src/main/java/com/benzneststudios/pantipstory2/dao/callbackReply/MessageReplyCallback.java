package com.benzneststudios.pantipstory2.dao.callbackReply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 03-Oct-17.
 */

public class MessageReplyCallback {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comment_no")
    @Expose
    private String commentNo;
    @SerializedName("reply_id")
    @Expose
    private Integer replyId;
    @SerializedName("reply_no")
    @Expose
    private Integer replyNo;
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
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("methodType")
    @Expose
    private String methodType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(String commentNo) {
        this.commentNo = commentNo;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

}
