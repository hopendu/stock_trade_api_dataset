package com.hackerrank.stocktrade.util;

import com.hackerrank.stocktrade.util.abstraction.IDateStringToTimestampConvertor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringToTimestampConvertor implements IDateStringToTimestampConvertor {

    private final SimpleDateFormat dateFormat;

    public DateStringToTimestampConvertor(String dateFormatPattern) {
        this.dateFormat = new SimpleDateFormat(dateFormatPattern);
    }

    @Override
    public Timestamp convert(String dateString) {
        try {
            Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error converting a String to Timestamp");
    }
}
