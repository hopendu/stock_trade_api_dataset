package com.hackerrank.stocktrade.model.response;

import com.hackerrank.stocktrade.model.Trade;

public class SearchTradesWithLowestAndHighestPriceQueryResponse {
    private Trade tradeWithLowestPrice;
    private Trade tradeWithHighestPrice;

    public Trade getTradeWithHighestPrice() {
        return tradeWithHighestPrice;
    }

    public void setTradeWithHighestPrice(Trade tradeWithHighestPrice) {
        this.tradeWithHighestPrice = tradeWithHighestPrice;
    }

    public Trade getTradeWithLowestPrice() {
        return tradeWithLowestPrice;
    }

    public void setTradeWithLowestPrice(Trade tradeWithLowestPrice) {
        this.tradeWithLowestPrice = tradeWithLowestPrice;
    }
}
