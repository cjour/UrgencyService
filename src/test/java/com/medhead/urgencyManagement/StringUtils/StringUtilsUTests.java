package com.medhead.urgencyManagement.StringUtils;

import com.medhead.urgencyManagement.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class StringUtilsUTests {

    private final StringUtils stringUtils = new StringUtils();

    @Test
    public void buildCoordinates_should_concatenate_latitude_and_longitude_with_space_separator() {
        //GIVEN
        String latitude = "-36.848374";
        String longitude = "174.763556";

        //WHEN
        String result = stringUtils.buildCoordinates(latitude, longitude, StringUtils.spaceSeparator);

        //THEN
        Assertions.assertEquals(result, latitude + StringUtils.spaceSeparator + longitude);
    }
}