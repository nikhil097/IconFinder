package com.app.nikhil.iconfinderapp.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Iconset {

    ArrayList<IconPrice> prices;


    String are_all_icons_glyph;

    IconAuthor author;

    ArrayList<IconStyle> styles;

    String published_at;

    String name;

    String is_premium;

    String iconset_id;

    String type;

    String identifier;

    ArrayList<IconCategory> categories;

    String icons_count;

    public ArrayList<IconPrice> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<IconPrice> prices) {
        this.prices = prices;
    }

    public String getAre_all_icons_glyph() {
        return are_all_icons_glyph;
    }

    public void setAre_all_icons_glyph(String are_all_icons_glyph) {
        this.are_all_icons_glyph = are_all_icons_glyph;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public IconAuthor getAuthor() {
        return author;
    }

    public void setAuthor(IconAuthor author) {
        this.author = author;
    }

    public ArrayList<IconStyle> getStyles() {
        return styles;
    }

    public void setStyles(ArrayList<IconStyle> styles) {
        this.styles = styles;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public String getIconset_id() {
        return iconset_id;
    }

    public void setIconset_id(String iconset_id) {
        this.iconset_id = iconset_id;
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

    public String getIcons_count() {
        return icons_count;
    }

    public void setIcons_count(String icons_count) {
        this.icons_count = icons_count;
    }
}
