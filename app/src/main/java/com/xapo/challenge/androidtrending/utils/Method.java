package com.xapo.challenge.androidtrending.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Method {
    public static String dateFormatter(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat dformatter = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        try {
            return dformatter.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

    }

}
