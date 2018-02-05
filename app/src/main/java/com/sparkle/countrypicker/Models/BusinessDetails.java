package com.sparkle.countrypicker.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 31-01-2018.
 */

public class BusinessDetails {

    @SerializedName("bussiness_title")
    String bussiness_title;

    @SerializedName("bussiness_phone")
    String bussiness_phone;

    @SerializedName("bussiness_whatsapp")
    String[] bussiness_whatsapp;

    @SerializedName("business_viber")
    String[] business_viber;

    @SerializedName("bussiness_website")
    String[] bussiness_website;

    @SerializedName("bussiness_email")
    String[] bussiness_email;

    @SerializedName("business_facebook")
    String[] business_facebook;

    @SerializedName("bussiness_twitter")
    String[] bussiness_twitter;

    @SerializedName("bussiness_instagram")
    String[] bussiness_instagram;

    @SerializedName("bussiness_linkedin")
    String[] bussiness_linkedin;

    @SerializedName("gallery")
    List<Gallery> gallery;

    public String getBussiness_title() {
        return bussiness_title;
    }

    public void setBussiness_title(String bussiness_title) {
        this.bussiness_title = bussiness_title;
    }

    public String getBussiness_phone() {
        return bussiness_phone;
    }

    public void setBussiness_phone(String bussiness_phone) {
        this.bussiness_phone = bussiness_phone;
    }

    public String[] getBussiness_whatsapp() {
        return bussiness_whatsapp;
    }

    public void setBussiness_whatsapp(String[] bussiness_whatsapp) {
        this.bussiness_whatsapp = bussiness_whatsapp;
    }

    public String[] getBusiness_viber() {
        return business_viber;
    }

    public void setBusiness_viber(String[] business_viber) {
        this.business_viber = business_viber;
    }

    public String[] getBussiness_website() {
        return bussiness_website;
    }

    public void setBussiness_website(String[] bussiness_website) {
        this.bussiness_website = bussiness_website;
    }

    public String[] getBussiness_email() {
        return bussiness_email;
    }

    public void setBussiness_email(String[] bussiness_email) {
        this.bussiness_email = bussiness_email;
    }

    public String[] getBusiness_facebook() {
        return business_facebook;
    }

    public void setBusiness_facebook(String[] business_facebook) {
        this.business_facebook = business_facebook;
    }

    public String[] getBussiness_twitter() {
        return bussiness_twitter;
    }

    public void setBussiness_twitter(String[] bussiness_twitter) {
        this.bussiness_twitter = bussiness_twitter;
    }

    public String[] getBussiness_instagram() {
        return bussiness_instagram;
    }

    public void setBussiness_instagram(String[] bussiness_instagram) {
        this.bussiness_instagram = bussiness_instagram;
    }

    public String[] getBussiness_linkedin() {
        return bussiness_linkedin;
    }

    public void setBussiness_linkedin(String[] bussiness_linkedin) {
        this.bussiness_linkedin = bussiness_linkedin;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public class Gallery implements Serializable {
        @SerializedName("id")
        String id;

        @SerializedName("name")
        String name;

        @SerializedName("image")
        List<Image> image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Image> getImage() {
            return image;
        }

        public void setImage(List<Image> image) {
            this.image = image;
        }
    }

    public class Image implements Serializable {
        @SerializedName("id")
        String id;

        @SerializedName("path")
        String path;

        @SerializedName("most_viewed")
        String most_viewed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMost_viewed() {
            return most_viewed;
        }

        public void setMost_viewed(String most_viewed) {
            this.most_viewed = most_viewed;
        }
    }
}
