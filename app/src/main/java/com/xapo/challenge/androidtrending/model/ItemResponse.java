package com.xapo.challenge.androidtrending.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemResponse implements Serializable{
    @SerializedName("items")

    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemResponse{" +
                "items=" + items +
                '}';
    }
}
