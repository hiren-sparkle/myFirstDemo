package com.sparkle.countrypicker.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 23-01-2018.
 */

public class ListBusinessArr implements Serializable {
    @SerializedName("data")
    List<ListBusiness> data;

    @SerializedName("message")
    String message;

    @SerializedName("status")
    String status;

    @SerializedName("next_page")
    String next_page;

    public List<ListBusiness> getData() {
        return data;
    }

    public void setData(List<ListBusiness> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }
}
