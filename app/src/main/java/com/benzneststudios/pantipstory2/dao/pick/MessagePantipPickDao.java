package com.benzneststudios.pantipstory2.dao.pick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 01-Oct-17.
 */

public class MessagePantipPickDao {
    @SerializedName("pick1")
    @Expose
    private List<Pick1Dao> pick1 = null;
    @SerializedName("pick3")
    @Expose
    private List<Pick3Dao> pick3 = null;
    @SerializedName("last_time")
    @Expose
    private String lastTime;

    public List<Pick1Dao> getPick1() {
        return pick1;
    }

    public void setPick1(List<Pick1Dao> pick1) {
        this.pick1 = pick1;
    }

    public List<Pick3Dao> getPick3() {
        return pick3;
    }

    public void setPick3(List<Pick3Dao> pick3) {
        this.pick3 = pick3;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
