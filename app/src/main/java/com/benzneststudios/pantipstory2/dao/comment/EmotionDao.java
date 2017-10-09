package com.benzneststudios.pantipstory2.dao.comment;

import com.benzneststudios.pantipstory2.dao.comment.mood.ImpressDao;
import com.benzneststudios.pantipstory2.dao.comment.mood.LaughDao;
import com.benzneststudios.pantipstory2.dao.comment.mood.LikeDao;
import com.benzneststudios.pantipstory2.dao.comment.mood.LoveDao;
import com.benzneststudios.pantipstory2.dao.comment.mood.ScaryDao;
import com.benzneststudios.pantipstory2.dao.comment.mood.SurprisedDao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 30-Sep-17.
 */

public class EmotionDao {
    @SerializedName("sum")
    @Expose
    private Integer sum;
    @SerializedName("latest")
    @Expose
    private List<LastestDao> latest = null;
    @SerializedName("like")
    @Expose
    private LikeDao like;
    @SerializedName("laugh")
    @Expose
    private LaughDao laugh;
    @SerializedName("love")
    @Expose
    private LoveDao love;
    @SerializedName("impress")
    @Expose
    private ImpressDao impress;
    @SerializedName("scary")
    @Expose
    private ScaryDao scary;
    @SerializedName("surprised")
    @Expose
    private SurprisedDao surprised;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<LastestDao> getLatest() {
        return latest;
    }

    public void setLatest(List<LastestDao> latest) {
        this.latest = latest;
    }

    public LikeDao getLike() {
        return like;
    }

    public void setLike(LikeDao like) {
        this.like = like;
    }

    public LaughDao getLaugh() {
        return laugh;
    }

    public void setLaugh(LaughDao laugh) {
        this.laugh = laugh;
    }

    public LoveDao getLove() {
        return love;
    }

    public void setLove(LoveDao love) {
        this.love = love;
    }

    public ImpressDao getImpress() {
        return impress;
    }

    public void setImpress(ImpressDao impress) {
        this.impress = impress;
    }

    public ScaryDao getScary() {
        return scary;
    }

    public void setScary(ScaryDao scary) {
        this.scary = scary;
    }

    public SurprisedDao getSurprised() {
        return surprised;
    }

    public void setSurprised(SurprisedDao surprised) {
        this.surprised = surprised;
    }
}
