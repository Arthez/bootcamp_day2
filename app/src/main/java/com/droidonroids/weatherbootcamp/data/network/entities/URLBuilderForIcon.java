package com.droidonroids.weatherbootcamp.data.network.entities;

public class URLBuilderForIcon {
    public static String URLBuilderForIcon(String iconId) {
        String url = "http://openweathermap.org/img/w/";
        url += iconId;
        url += ".png";
        return url;
    }

}
