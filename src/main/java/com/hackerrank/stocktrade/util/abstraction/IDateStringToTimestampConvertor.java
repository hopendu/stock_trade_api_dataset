package com.hackerrank.stocktrade.util.abstraction;

import java.sql.Timestamp;

public interface IDateStringToTimestampConvertor {
    Timestamp convert(String date);
}
