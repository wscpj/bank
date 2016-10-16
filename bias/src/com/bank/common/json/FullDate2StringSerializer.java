package com.bank.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.bank.common.AppConstants;

public class FullDate2StringSerializer extends JsonSerializer<Date>{

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.FORMMAT_DATE_PATTERN);
        formatter.setTimeZone(TimeZone.getTimeZone(AppConstants.TIMEZONE_AISA_SHANGHAI));
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);
    }


}
