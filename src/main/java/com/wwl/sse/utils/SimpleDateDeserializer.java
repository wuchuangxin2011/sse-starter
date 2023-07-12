package com.wwl.sse.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class SimpleDateDeserializer extends DateDeserializers.DateDeserializer {
    private static final long serialVersionUID = -6218693745160760598L;
    ArrayList<DateFormat> dateFormatList = new ArrayList<>();

    public SimpleDateDeserializer(String... dateFormat) {
        if (dateFormat != null && dateFormat.length > 0) {
            for (int i = 0; i < dateFormat.length; i++) {
                DateFormat df = new SimpleDateFormat(dateFormat[i]);
                dateFormatList.add(df);
            }
        }
    }

    @Override
    protected Date _parseDate(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Date _parseDate;
        try {
            _parseDate = super._parseDate(jp, ctxt);
        } catch (Exception ex) {
            String dateStr = jp.getText().trim();
            for (int i = 0; i < dateFormatList.size(); i++) {
                try {
                    return dateFormatList.get(i).parse(dateStr);
                } catch (ParseException e) {
                }
            }
            try {
                long tick = jp.getLongValue();
                return new Date(tick);
            } catch (Exception e) {
            }

            throw ex;
        }
        return _parseDate;
    }
}
