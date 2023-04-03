package com.medhead.urgencyManagement.utils;

public class StringUtils {
    private static final String spaceSeparator = "%2C";

    public String buildCoordinates(String latitude, String longitude) {
        return latitude + spaceSeparator + longitude;
    }
}
