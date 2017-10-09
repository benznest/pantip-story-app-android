package com.benzneststudios.pantipstory2.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 30-Sep-17.
 */

public class CommentDao {
    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("comment_no")
    @Expose
    private Integer commentNo;
    @SerializedName("user")
    @Expose
    private UserDao user;
    @SerializedName("reply_count")
    @Expose
    private Integer replyCount;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("action_id")
    @Expose
    private ActionIdDao actionId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("emotion")
    @Expose
    private EmotionDao emotion;
    @SerializedName("replies")
    @Expose
    private List<ReplyDao> replies = null;
    @SerializedName("emo_score")
    @Expose
    private Integer emoScore;
    @SerializedName("good_bad_vote")
    @Expose
    private GoodBadVoteDao goodBadVote;
    @SerializedName("topic_status")
    @Expose
    private Integer topicStatus;
    @SerializedName("email_user")
    @Expose
    private Boolean emailUser;
    @SerializedName("owner_topic")
    @Expose
    private Boolean ownerTopic;
    @SerializedName("data_utime")
    @Expose
    private String dataUtime;
    @SerializedName("data_addrtitle")
    @Expose
    private String dataAddrtitle;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("ref_reply")
    @Expose
    private String refReply;
    @SerializedName("ref_reply_id")
    @Expose
    private String refReplyId;
    @SerializedName("ref_cmt_no_report")
    @Expose
    private String refCmtNoReport;
    @SerializedName("ref_cmt_report")
    @Expose
    private String refCmtReport;
    @SerializedName("first_reply")
    @Expose
    private Integer firstReply;
    @SerializedName("last_reply")
    @Expose
    private Integer lastReply;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public ActionIdDao getActionId() {
        return actionId;
    }

    public void setActionId(ActionIdDao actionId) {
        this.actionId = actionId;
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

    public EmotionDao getEmotion() {
        return emotion;
    }

    public void setEmotion(EmotionDao emotion) {
        this.emotion = emotion;
    }

    public List<ReplyDao> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyDao> replies) {
        this.replies = replies;
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

    public Integer getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(Integer topicStatus) {
        this.topicStatus = topicStatus;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getRefReply() {
        return refReply;
    }

    public void setRefReply(String refReply) {
        this.refReply = refReply;
    }

    public String getRefReplyId() {
        return refReplyId;
    }

    public void setRefReplyId(String refReplyId) {
        this.refReplyId = refReplyId;
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

    public Integer getFirstReply() {
        return firstReply;
    }

    public void setFirstReply(Integer firstReply) {
        this.firstReply = firstReply;
    }

    public Integer getLastReply() {
        return lastReply;
    }

    public void setLastReply(Integer lastReply) {
        this.lastReply = lastReply;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOwnerTopic() {
        return ownerTopic;
    }

    public void setOwnerTopic(Boolean ownerTopic) {
        this.ownerTopic = ownerTopic;
    }
}
