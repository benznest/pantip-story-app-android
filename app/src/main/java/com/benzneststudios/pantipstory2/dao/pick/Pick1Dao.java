package com.benzneststudios.pantipstory2.dao.pick;

import com.benzneststudios.pantipstory2.dao.topic.TagDao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 01-Oct-17.
 */

public class Pick1Dao {
    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("shard_key")
    @Expose
    private String shardKey;
    @SerializedName("disp_topic")
    @Expose
    private String dispTopic;
    @SerializedName("disp_msg")
    @Expose
    private String dispMsg;
    @SerializedName("cover_img")
    @Expose
    private String coverImg;
    @SerializedName("img")
    @Expose
    private List<String> img;
    @SerializedName("publish_time")
    @Expose
    private PublishTimeDao publishTime;
    @SerializedName("updated_time")
    @Expose
    private UpdatedTimeDao updatedTime;
    @SerializedName("topic_status")
    @Expose
    private Integer topicStatus;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;
    @SerializedName("ip_x")
    @Expose
    private String ipX;
    @SerializedName("ip_a")
    @Expose
    private String ipA;
    @SerializedName("created_time")
    @Expose
    private CreatedTimeDao createdTime;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("tags")
    @Expose
    private List<TagDao> tags = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mid")
    @Expose
    private Integer mid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShardKey() {
        return shardKey;
    }

    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
    }

    public String getDispTopic() {
        return dispTopic;
    }

    public void setDispTopic(String dispTopic) {
        this.dispTopic = dispTopic;
    }

    public String getDispMsg() {
        return dispMsg;
    }

    public void setDispMsg(String dispMsg) {
        this.dispMsg = dispMsg;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public PublishTimeDao getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(PublishTimeDao publishTime) {
        this.publishTime = publishTime;
    }

    public UpdatedTimeDao getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(UpdatedTimeDao updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(Integer topicStatus) {
        this.topicStatus = topicStatus;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getIpX() {
        return ipX;
    }

    public void setIpX(String ipX) {
        this.ipX = ipX;
    }

    public String getIpA() {
        return ipA;
    }

    public void setIpA(String ipA) {
        this.ipA = ipA;
    }

    public CreatedTimeDao getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(CreatedTimeDao createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<TagDao> getTags() {
        return tags;
    }

    public void setTags(List<TagDao> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

}
