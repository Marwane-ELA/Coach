package com.marwane.coach.tools;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MyTools {

    /**
     * convert a string to  a data
     * Tue Aug 07 05:47:25 EDT 2018 (EEE MMM dd hh:mm:ss 'EDT" yyyy)--> Date
     * @param unedate
     * @return
     */
    public static Date convertStringToDate(String unedate ){
        String format = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(unedate);
            return date;
        }catch(ParseException e){
            Log.d("Error", "parse date impossible" + e.toString());
        }
        return null;
    }

    /**
     * convert date to a string
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return date.format(uneDate);
    }
}
