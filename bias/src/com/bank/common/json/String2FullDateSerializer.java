package com.bank.common.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.bank.common.AppConstants;

public class String2FullDateSerializer extends JsonDeserializer<Date>{

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext arg1)
            throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat(AppConstants.FORMMAT_DATE_PATTERN);
        format.setTimeZone(TimeZone.getTimeZone(AppConstants.TIMEZONE_AISA_SHANGHAI));
        String date = jsonParser.getText();
        if(date == null || date.toString().equals("")) {
            return null;
        }
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}
