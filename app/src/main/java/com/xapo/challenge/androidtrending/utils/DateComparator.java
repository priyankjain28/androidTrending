package com.xapo.challenge.androidtrending.utils;

import com.xapo.challenge.androidtrending.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
        //In the following line you set the criterion,
        //which is the name of Contact in my example scenario
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(item1.getUpdatedTime());
            d2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(item2.getUpdatedTime());
            return d2.compareTo(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}