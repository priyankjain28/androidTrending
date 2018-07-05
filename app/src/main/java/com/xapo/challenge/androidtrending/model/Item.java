package com.xapo.challenge.androidtrending.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Item implements Serializable{
	@SerializedName("name")
	private String name;
	@SerializedName("full_name")
	private String fullName;
	@SerializedName("description")
	private String description;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("homepage")
	private String homePage;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("language")
    private String language;
    @SerializedName("stargazers_count")
	private String starsCount;
    @SerializedName("forks")
	private String forks;
    @SerializedName("html_url")
	private String url;
    @SerializedName("updated_at")
	private String updatedTime;
	@SerializedName("license")
	private License license;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStarsCount() {
		return starsCount;
	}

	public void setStarsCount(String starsCount) {
		this.starsCount = starsCount;
	}

	public String getForks() {
		return forks;
	}

	public void setForks(String forks) {
		this.forks = forks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getDateAgo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		SimpleDateFormat dformatter = new SimpleDateFormat("MMM dd,yyyy");
		try {
			return "Updated on "+dformatter.format(sdf.parse(updatedTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String toString() {
		return "Item{" +
				"name='" + name + '\'' +
				", fullName='" + fullName + '\'' +
				", description='" + description + '\'' +
				", createdAt='" + createdAt + '\'' +
				", homePage='" + homePage + '\'' +
				", owner=" + owner +
				", language='" + language + '\'' +
				", starsCount='" + starsCount + '\'' +
				", forks='" + forks + '\'' +
				", url='" + url + '\'' +
				", updatedTime='" + updatedTime + '\'' +
				", license=" + license +
				'}';
	}
}