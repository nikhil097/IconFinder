package com.app.nikhil.iconfinderapp.ResponseBody;

import com.app.nikhil.iconfinderapp.Pojo.Iconset;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IconSetResponseBody {

    @SerializedName("iconsets")
    ArrayList<Iconset> iconsets;

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

    public ArrayList<Iconset> getIconsets() {
        return iconsets;
    }

    public void setIconsets(ArrayList<Iconset> iconsets) {
        this.iconsets = iconsets;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }
}
