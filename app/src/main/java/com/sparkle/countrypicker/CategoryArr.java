package com.sparkle.countrypicker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 22-01-2018.
 */

public class CategoryArr {
    @SerializedName("data")
    List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }
}
