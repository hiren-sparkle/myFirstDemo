package com.sparkle.countrypicker;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 22-01-2018.
 */

public class Category {

    @SerializedName("category_id")
    String category_id;

    @SerializedName("category_title")
    String category_title;

    @SerializedName("category_image")
    String category_image;

    @SerializedName("parent_category_id")
    String parent_category_id;

    @SerializedName("subCategory")
    List<SubCategory> subCategory;

    boolean isSelected;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(String parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

    public List<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public class SubCategory implements Serializable {
        @SerializedName("category_id")
        String category_id;

        @SerializedName("category_title")
        String category_title;

        @SerializedName("category_image")
        String category_image;

        @SerializedName("parent_category_id")
        String parent_category_id;

        boolean isSelected;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_title() {
            return category_title;
        }

        public void setCategory_title(String category_title) {
            this.category_title = category_title;
        }

        public String getCategory_image() {
            return category_image;
        }

        public void setCategory_image(String category_image) {
            this.category_image = category_image;
        }

        public String getParent_category_id() {
            return parent_category_id;
        }

        public void setParent_category_id(String parent_category_id) {
            this.parent_category_id = parent_category_id;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
