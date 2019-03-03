package com.benznestdeveloper.pantipstory.dao.comment;

import com.benznestdeveloper.pantipstory.dao.comment.social.BloggangDao;
import com.benznestdeveloper.pantipstory.dao.comment.social.FacebookDao;
import com.benznestdeveloper.pantipstory.dao.comment.social.GoogleDao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class UserMetaDao {

    @SerializedName("bloggang")
    @Expose
    private BloggangDao bloggang;
    @SerializedName("facebook")
    @Expose
    private FacebookDao facebook;
    @SerializedName("icon")
    @Expose
    private IconDao icon;
    @SerializedName("google")
    @Expose
    private GoogleDao google;
    @SerializedName("other")
    @Expose
    private OtherDao other;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("verified")
    @Expose
    private Boolean verified;

    public IconDao getIcon() {
        return icon;
    }

    public void setIcon(IconDao icon) {
        this.icon = icon;
    }

    public OtherDao getOther() {
        return other;
    }

    public void setOther(OtherDao other) {
        this.other = other;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
