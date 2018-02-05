package com.sparkle.countrypicker.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 23-01-2018.
 */

public class ListBusiness implements Serializable {

    @SerializedName("bussiness_id")
    String bussiness_id;

    @SerializedName("business_user_id")
    String business_user_id;

    @SerializedName("business_logo")
    String business_logo;

    @SerializedName("business_banner_image")
    String business_banner_image;

    @SerializedName("bussiness_title")
    String bussiness_title;

    @SerializedName("bussiness_status")
    String bussiness_status;

    @SerializedName("bussiness_address")
    String bussiness_address;

    @SerializedName("bussiness_city")
    String bussiness_city;

    @SerializedName("bussiness_country")
    String bussiness_country;

    @SerializedName("parent_cat")
    String parent_cat;

    @SerializedName("child_cat")
    String child_cat;

    public String getBussiness_id() {
        return bussiness_id;
    }

    public void setBussiness_id(String bussiness_id) {
        this.bussiness_id = bussiness_id;
    }

    public String getBusiness_user_id() {
        return business_user_id;
    }

    public void setBusiness_user_id(String business_user_id) {
        this.business_user_id = business_user_id;
    }

    public String getBusiness_logo() {
        return business_logo;
    }

    public void setBusiness_logo(String business_logo) {
        this.business_logo = business_logo;
    }

    public String getBusiness_banner_image() {
        return business_banner_image;
    }

    public void setBusiness_banner_image(String business_banner_image) {
        this.business_banner_image = business_banner_image;
    }

    public String getBussiness_title() {
        return bussiness_title;
    }

    public void setBussiness_title(String bussiness_title) {
        this.bussiness_title = bussiness_title;
    }

    public String getBussiness_status() {
        return bussiness_status;
    }

    public void setBussiness_status(String bussiness_status) {
        this.bussiness_status = bussiness_status;
    }

    public String getBussiness_address() {
        return bussiness_address;
    }

    public void setBussiness_address(String bussiness_address) {
        this.bussiness_address = bussiness_address;
    }

    public String getBussiness_city() {
        return bussiness_city;
    }

    public void setBussiness_city(String bussiness_city) {
        this.bussiness_city = bussiness_city;
    }

    public String getBussiness_country() {
        return bussiness_country;
    }

    public void setBussiness_country(String bussiness_country) {
        this.bussiness_country = bussiness_country;
    }

    public String getParent_cat() {
        return parent_cat;
    }

    public void setParent_cat(String parent_cat) {
        this.parent_cat = parent_cat;
    }

    public String getChild_cat() {
        return child_cat;
    }

    public void setChild_cat(String child_cat) {
        this.child_cat = child_cat;
    }
}
