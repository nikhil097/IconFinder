package com.app.nikhil.iconfinderapp.ResponseBody;

import com.app.nikhil.iconfinderapp.Pojo.Icon;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IconResponseBody {

    @SerializedName("icons")
    ArrayList<Icon> icons;

    @SerializedName("total_count")
    String total_count;

    @SerializedName("message")
    String message;

    @SerializedName("code")
    int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Icon> getIcons() {
        return icons;
    }

    public void setIcons(ArrayList<Icon> icons) {
        this.icons = icons;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}
