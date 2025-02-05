package com.hackerrank.stocktrade.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CustomTimestampDeserializer extends JsonDeserializer<Timestamp> {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Timestamp deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String date = parser.getText();
        try {
            return new Timestamp(sdf.parse(date).getTime());
        } catch (Exception e) {
            throw new IOException("Error parsing date: " + date, e);
        }
    }
}