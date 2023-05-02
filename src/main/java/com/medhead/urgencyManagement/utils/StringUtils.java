package com.medhead.urgencyManagement.utils;

public class StringUtils {
    public static final String spaceSeparator = "%2C";

    public String buildCoordinates(String latitude, String longitude, String separator) {
        return latitude + separator + longitude;
    }
}
