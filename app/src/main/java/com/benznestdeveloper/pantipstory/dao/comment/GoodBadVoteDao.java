package com.benznestdeveloper.pantipstory.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class GoodBadVoteDao {
    @SerializedName("good_voted")
    @Expose
    private String goodVoted;
    @SerializedName("bad_voted")
    @Expose
    private String badVoted;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("class_score")
    @Expose
    private String classScore;

    public String getGoodVoted() {
        return goodVoted;
    }

    public void setGoodVoted(String goodVoted) {
        this.goodVoted = goodVoted;
    }

    public String getBadVoted() {
        return badVoted;
    }

    public void setBadVoted(String badVoted) {
        this.badVoted = badVoted;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getClassScore() {
        return classScore;
    }

    public void setClassScore(String classScore) {
        this.classScore = classScore;
    }
}
