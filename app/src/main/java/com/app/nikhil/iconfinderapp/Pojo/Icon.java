package com.app.nikhil.iconfinderapp.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Icon {


    boolean is_premium;

    String is_icon_glyph;

    ArrayList<String> tags;

    String published_at;

    String icon_id;

    String type;

    ArrayList<IconPrice> prices;

    ArrayList<IconCategory> categories;

    ArrayList<IconStyle> styles;

    @SerializedName("vector_sizes")
    ArrayList<ImageVectorSize> vector_Sizes;

    @SerializedName("raster_sizes")
    ArrayList<IconRasterSize> raster_Sizes;

    public boolean getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(boolean is_premium) {
        this.is_premium = is_premium;
    }

    public String getIs_icon_glyph() {
        return is_icon_glyph;
    }

    public void setIs_icon_glyph(String is_icon_glyph) {
        this.is_icon_glyph = is_icon_glyph;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public boolean isIs_premium() {
        return is_premium;
    }

    public ArrayList<IconPrice> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<IconPrice> prices) {
        this.prices = prices;
    }

    public ArrayList<ImageVectorSize> getVector_Sizes() {
        return vector_Sizes;
    }

    public void setVector_Sizes(ArrayList<ImageVectorSize> vector_Sizes) {
        this.vector_Sizes = vector_Sizes;
    }

    public ArrayList<IconRasterSize> getRaster_Sizes() {
        return raster_Sizes;
    }

    public void setRaster_Sizes(ArrayList<IconRasterSize> raster_Sizes) {
        this.raster_Sizes = raster_Sizes;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(String icon_id) {
        this.icon_id = icon_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<IconCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<IconCategory> categories) {
        this.categories = categories;
    }

    public ArrayList<IconStyle> getStyles() {
        return styles;
    }

    public void setStyles(ArrayList<IconStyle> styles) {
        this.styles = styles;
    }

    public ArrayList<ImageVectorSize> getVectorSizes() {
        return vector_Sizes;
    }

    public void setVectorSizes(ArrayList<ImageVectorSize> vectorSizes) {
        this.vector_Sizes = vectorSizes;
    }

    public ArrayList<IconRasterSize> getRasterSizes() {
        return raster_Sizes;
    }

    public void setRasterSizes(ArrayList<IconRasterSize> rasterSizes) {
        this.raster_Sizes = rasterSizes;
    }
}
