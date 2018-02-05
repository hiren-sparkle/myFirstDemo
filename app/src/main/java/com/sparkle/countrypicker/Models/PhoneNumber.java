package com.sparkle.countrypicker.Models;

/**
 * Created by Admin on 31-01-2018.
 */

public class PhoneNumber {
    String key;
    String phoneNumber;

    public PhoneNumber(String key, String phoneNumber) {
        this.key = key;
        this.phoneNumber = phoneNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
