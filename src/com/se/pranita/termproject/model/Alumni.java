package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;

/**
 * Created by Pranita on 22/4/16.
 */
public class Alumni {
    @Override
    public String toString() {
        return toJSON();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if(image != null)
            this.image = image;
        else
            this.image = "";
    }

    public Alumni() {

    }

    public Alumni(String name, String homepage, String description, String image) {
        this.name = name;
        this.homepage = homepage;
        this.description = description;
        this.image = image;
    }

    private String name;
    private String homepage;
    private String description;
    private String image;

    public String toJSON(){
        return new GsonBuilder().create().toJson(this);
    }
}
