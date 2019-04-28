package com.app.nikhil.iconfinderapp.Pojo;

import com.google.gson.annotations.SerializedName;

public class IconAuthor {

    String is_designer;

    String iconsets_count;

    String name;

    String userId;

    String userName;

    public String getIs_designer() {
        return is_designer;
    }

    public void setIs_designer(String is_designer) {
        this.is_designer = is_designer;
    }

    public String getIconsets_count() {
        return iconsets_count;
    }

    public void setIconsets_count(String iconsets_count) {
        this.iconsets_count = iconsets_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
