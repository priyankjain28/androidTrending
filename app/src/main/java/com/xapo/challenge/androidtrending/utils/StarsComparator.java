package com.xapo.challenge.androidtrending.utils;

import com.xapo.challenge.androidtrending.model.Item;

import java.util.Comparator;

public class StarsComparator implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
        //In the following line you set the criterion,
        //which is the name of Contact in my example scenario
        return Integer.parseInt(item2.getStarsCount())-Integer.parseInt(item1.getStarsCount());
    }
}