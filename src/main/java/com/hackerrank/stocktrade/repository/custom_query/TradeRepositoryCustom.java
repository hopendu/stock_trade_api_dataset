package com.hackerrank.stocktrade.repository.custom_query;

import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;

import java.util.Date;
import java.util.List;

public interface TradeRepositoryCustom {
    List<SearchStocksQueryResponse> findHighestAndLowestPriceByStockSymbolInDateRange(
            String symbol,
            Date startDate,
            Date endDate
    );
}
