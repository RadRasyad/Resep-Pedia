package com.rpl.reseppedia.model.remote.response;

import com.google.gson.annotations.SerializedName;

public class RecipeResponse {

    @SerializedName("title")
    private String name;

    @SerializedName("thumb")
    private String imageThumb;

    @SerializedName("key")
    private String key;

    @SerializedName("times")
    private String times;

    @SerializedName("portion")
    private String portion;

    @SerializedName("dificulty")
    private String dificulty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }
}
