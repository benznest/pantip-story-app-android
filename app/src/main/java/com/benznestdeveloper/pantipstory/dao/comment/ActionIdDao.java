package com.benznestdeveloper.pantipstory.dao.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benznest on 30-Sep-17.
 */

public class ActionIdDao {
    @SerializedName("$ref")
    @Expose
    private String $ref;
    @SerializedName("$id")
    @Expose
    private Integer $id;

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    public Integer get$id() {
        return $id;
    }

    public void set$id(Integer $id) {
        this.$id = $id;
    }
}
