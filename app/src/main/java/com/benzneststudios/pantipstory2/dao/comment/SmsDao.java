package com.benzneststudios.pantipstory2.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class SmsDao {
    @SerializedName("pt")
    @Expose
    private Integer pt;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("date_active")
    @Expose
    private DateActiveDao dateActive;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getPt() {
        return pt;
    }

    public void setPt(Integer pt) {
        this.pt = pt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public DateActiveDao getDateActive() {
        return dateActive;
    }

    public void setDateActive(DateActiveDao dateActive) {
        this.dateActive = dateActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
