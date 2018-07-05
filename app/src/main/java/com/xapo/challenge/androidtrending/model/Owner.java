package com.xapo.challenge.androidtrending.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Owner implements Serializable{
    @SerializedName("avatar_url")
	private String avatarUrl;

    @SerializedName("url")
    private String url;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}