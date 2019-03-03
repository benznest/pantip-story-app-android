package com.benznestdeveloper.pantipstory.dao.callbackEmotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Oct-17.
 */

public class MessageCallbackEmotionDao {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("emotion")
    @Expose
    private MyCallbackEmotionDao emotion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyCallbackEmotionDao getEmotion() {
        return emotion;
    }

    public void setEmotion(MyCallbackEmotionDao emotion) {
        this.emotion = emotion;
    }
}
