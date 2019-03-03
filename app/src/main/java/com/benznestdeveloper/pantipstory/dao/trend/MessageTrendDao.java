package com.benznestdeveloper.pantipstory.dao.trend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benznest on 30-Sep-17.
 */

public class MessageTrendDao {

    @SerializedName("related")
    @Expose
    private Object related;
    @SerializedName("trend")
    @Expose
    private List<TrendDao> trend = null;

    public Object getRelated() {
        return related;
    }

    public void setRelated(Object related) {
        this.related = related;
    }

    public List<TrendDao> getTrend() {
        return trend;
    }

    public void setTrend(List<TrendDao> trend) {
        this.trend = trend;
    }
}
