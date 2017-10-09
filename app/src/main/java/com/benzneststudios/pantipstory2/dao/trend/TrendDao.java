package com.benzneststudios.pantipstory2.dao.trend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class TrendDao {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("disp_topic")
    @Expose
    private String dispTopic;
    @SerializedName("max_trend")
    @Expose
    private String maxTrend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getDispTopic() {
        return dispTopic;
    }

    public void setDispTopic(String dispTopic) {
        this.dispTopic = dispTopic;
    }

    public String getMaxTrend() {
        return maxTrend;
    }

    public void setMaxTrend(String maxTrend) {
        this.maxTrend = maxTrend;
    }
}
