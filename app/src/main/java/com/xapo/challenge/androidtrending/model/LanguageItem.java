package com.xapo.challenge.androidtrending.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LanguageItem implements Serializable {

    @SerializedName("author")
    private String author;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("stars")
    private Integer stars;
    @SerializedName("forks")
    private Integer forks;
    @SerializedName("currentPeriodStars")
    private Integer currentPeriodStars;
    private final static long serialVersionUID = 1256265438993994372L;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(Integer currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    @Override
    public String toString() {
        return "LanguageItem{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", stars=" + stars +
                ", forks=" + forks +
                ", currentPeriodStars=" + currentPeriodStars +
                '}';
    }
}