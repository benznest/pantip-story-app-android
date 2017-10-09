package com.benzneststudios.pantipstory2.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class ReplyDao {
    @SerializedName("reply_id")
    @Expose
    private Integer replyId;
    @SerializedName("reply_no")
    @Expose
    private Integer replyNo;
    @SerializedName("comment_no")
    @Expose
    private Integer commentNo;
    @SerializedName("user")
    @Expose
    private UserDao user;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("ipv6")
    @Expose
    private String ipv6;
    @SerializedName("emotion")
    @Expose
    private EmotionDao emotion;
    @SerializedName("email_user")
    @Expose
    private Boolean emailUser;
    @SerializedName("data_utime")
    @Expose
    private String dataUtime;
    @SerializedName("data_addrtitle")
    @Expose
    private String dataAddrtitle;
    @SerializedName("owner_topic")
    @Expose
    private Boolean ownerTopic;
    @SerializedName("emo_score")
    @Expose
    private Integer emoScore;
    @SerializedName("good_bad_vote")
    @Expose
    private GoodBadVoteDao goodBadVote;
    @SerializedName("ref_cmt_no_report")
    @Expose
    private String refCmtNoReport;
    @SerializedName("ref_cmt_report")
    @Expose
    private String refCmtReport;
    @SerializedName("comment_id")
    @Expose
    private Integer commentId;
    @SerializedName("last_revision_no")
    @Expose
    private Integer lastRevisionNo;
    @SerializedName("last_modified")
    @Expose
    private Boolean lastModified;
    @SerializedName("last_mod_iso_time")
    @Expose
    private String lastModIsoTime;
    @SerializedName("last_modified_abbrtitle")
    @Expose
    private String lastModifiedAbbrtitle;

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

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public EmotionDao getEmotion() {
        return emotion;
    }

    public void setEmotion(EmotionDao emotion) {
        this.emotion = emotion;
    }

    public Boolean getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(Boolean emailUser) {
        this.emailUser = emailUser;
    }

    public String getDataUtime() {
        return dataUtime;
    }

    public void setDataUtime(String dataUtime) {
        this.dataUtime = dataUtime;
    }

    public String getDataAddrtitle() {
        return dataAddrtitle;
    }

    public void setDataAddrtitle(String dataAddrtitle) {
        this.dataAddrtitle = dataAddrtitle;
    }

    public Boolean getOwnerTopic() {
        return ownerTopic;
    }

    public void setOwnerTopic(Boolean ownerTopic) {
        this.ownerTopic = ownerTopic;
    }

    public Integer getEmoScore() {
        return emoScore;
    }

    public void setEmoScore(Integer emoScore) {
        this.emoScore = emoScore;
    }

    public GoodBadVoteDao getGoodBadVote() {
        return goodBadVote;
    }

    public void setGoodBadVote(GoodBadVoteDao goodBadVote) {
        this.goodBadVote = goodBadVote;
    }

    public String getRefCmtNoReport() {
        return refCmtNoReport;
    }

    public void setRefCmtNoReport(String refCmtNoReport) {
        this.refCmtNoReport = refCmtNoReport;
    }

    public String getRefCmtReport() {
        return refCmtReport;
    }

    public void setRefCmtReport(String refCmtReport) {
        this.refCmtReport = refCmtReport;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getLastRevisionNo() {
        return lastRevisionNo;
    }

    public void setLastRevisionNo(Integer lastRevisionNo) {
        this.lastRevisionNo = lastRevisionNo;
    }

    public Boolean getLastModified() {
        return lastModified;
    }

    public void setLastModified(Boolean lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModIsoTime() {
        return lastModIsoTime;
    }

    public void setLastModIsoTime(String lastModIsoTime) {
        this.lastModIsoTime = lastModIsoTime;
    }

    public String getLastModifiedAbbrtitle() {
        return lastModifiedAbbrtitle;
    }

    public void setLastModifiedAbbrtitle(String lastModifiedAbbrtitle) {
        this.lastModifiedAbbrtitle = lastModifiedAbbrtitle;
    }
}
