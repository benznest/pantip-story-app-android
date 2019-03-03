package com.benznestdeveloper.pantipstory.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class UserDao {
    @SerializedName("mid")
    @Expose
    private Integer mid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("avatar")
    @Expose
    private AvatarDao avatar;
    @SerializedName("user_meta")
    @Expose
    private UserMetaDao userMeta;
    @SerializedName("pstrust")
    @Expose
    private Boolean pstrust;
    @SerializedName("sms")
    @Expose
    private SmsDao sms;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AvatarDao getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarDao avatar) {
        this.avatar = avatar;
    }

    public UserMetaDao getUserMeta() {
        return userMeta;
    }

    public void setUserMeta(UserMetaDao userMeta) {
        this.userMeta = userMeta;
    }

    public Boolean getPstrust() {
        return pstrust;
    }

    public void setPstrust(Boolean pstrust) {
        this.pstrust = pstrust;
    }

    public SmsDao getSms() {
        return sms;
    }

    public void setSms(SmsDao sms) {
        this.sms = sms;
    }

}
