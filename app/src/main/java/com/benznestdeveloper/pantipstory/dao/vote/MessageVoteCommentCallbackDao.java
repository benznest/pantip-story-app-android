package com.benznestdeveloper.pantipstory.dao.vote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 04-Oct-17.
 */

public class MessageVoteCommentCallbackDao {
    @SerializedName("what_vote_type")
    @Expose
    private String whatVoteType;
    @SerializedName("vote_success")
    @Expose
    private Boolean voteSuccess;
    @SerializedName("vote_message")
    @Expose
    private String voteMessage;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("balance_time")
    @Expose
    private String balanceTime;
    @SerializedName("subtrahend_time")
    @Expose
    private List<String> subtrahendTime = null;

    public String getWhatVoteType() {
        return whatVoteType;
    }

    public void setWhatVoteType(String whatVoteType) {
        this.whatVoteType = whatVoteType;
    }

    public Boolean getVoteSuccess() {
        return voteSuccess;
    }

    public void setVoteSuccess(Boolean voteSuccess) {
        this.voteSuccess = voteSuccess;
    }

    public String getVoteMessage() {
        return voteMessage;
    }

    public void setVoteMessage(String voteMessage) {
        this.voteMessage = voteMessage;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getBalanceTime() {
        return balanceTime;
    }

    public void setBalanceTime(String balanceTime) {
        this.balanceTime = balanceTime;
    }

    public List<String> getSubtrahendTime() {
        return subtrahendTime;
    }

    public void setSubtrahendTime(List<String> subtrahendTime) {
        this.subtrahendTime = subtrahendTime;
    }
}
