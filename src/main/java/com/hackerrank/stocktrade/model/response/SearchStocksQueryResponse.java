package com.hackerrank.stocktrade.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SearchStocksQueryResponse {

    private String symbol;
    @JsonProperty("highest")
    private BigDecimal highestPrice;
    @JsonProperty("lowest")
    private BigDecimal lowestPrice;

    public SearchStocksQueryResponse(String symbol,
                                     BigDecimal highestPrice,
                                     BigDecimal lowestPrice) {
        this.symbol = symbol;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(BigDecimal highestPrice) {
        this.highestPrice = highestPrice;
    }

    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(BigDecimal lowestPrice) {
        this.lowestPrice = lowestPrice;
    }
}
