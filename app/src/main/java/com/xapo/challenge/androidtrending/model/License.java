package com.xapo.challenge.androidtrending.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class License implements Serializable {
		@SerializedName("name")
	    private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "License{" +
					"name='" + name + '\'' +
					'}';
		}
	}