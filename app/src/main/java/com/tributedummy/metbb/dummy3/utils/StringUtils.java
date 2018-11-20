package com.tributedummy.metbb.dummy3.utils;

import android.text.Html;
import android.text.Spanned;

import com.tributedummy.metbb.dummy3.classes.Concert;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class StringUtils {
    public static String formatConcertSlash(Concert concert) {
        return concert.getArtist().getName()+" / "+concert.getVenue().getName();
    }
    @SuppressWarnings("deprecation")
    public static Spanned formatConcertHTML(Concert concert)
    {
        return Html.fromHtml("<b>"+concert.getArtist().getName() + "</b> at <b>" +concert.getVenue().getName()+"</b>");
    }
    public static String formatRating(float rating) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(rating);
    }
}
