package com.sparkle.countrypicker.Models;

/**
 * Created by Admin on 25-01-2018.
 */

public class SelectedCategories {

    String categories;
    String subCategories;

    public SelectedCategories(String categories, String subCategories) {
        this.categories = categories;
        this.subCategories = subCategories;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(String subCategories) {
        this.subCategories = subCategories;
    }
}
