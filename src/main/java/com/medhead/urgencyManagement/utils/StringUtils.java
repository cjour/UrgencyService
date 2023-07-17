package com.medhead.urgencyManagement.utils;

public class StringUtils {
    public static final String spaceSeparator = ",";

    public String buildCoordinates(String latitude, String longitude, String separator) {
        return latitude + separator + longitude;
    }
}
