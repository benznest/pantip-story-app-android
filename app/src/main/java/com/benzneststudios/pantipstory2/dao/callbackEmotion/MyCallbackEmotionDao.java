package com.benzneststudios.pantipstory2.dao.callbackEmotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 04-Oct-17.
 */

public class MyCallbackEmotionDao {
    @SerializedName("emo_type")
    @Expose
    private String emoType;
    @SerializedName("check")
    @Expose
    private Boolean check;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;

    public String getEmoType() {
        return emoType;
    }

    public void setEmoType(String emoType) {
        this.emoType = emoType;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
