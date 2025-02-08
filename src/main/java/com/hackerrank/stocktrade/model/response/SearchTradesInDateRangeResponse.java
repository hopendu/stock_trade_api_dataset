package com.hackerrank.stocktrade.model.response;

import com.hackerrank.stocktrade.model.Trade;

import java.util.List;

public class SearchTradesInDateRangeResponse {
    private List<Trade> trades;

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }
}
