package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StocksService {

    private final TradeRepository tradeRepository;

    @Autowired
    public StocksService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<SearchStocksQueryResponse> findHighestAndLowestPriceByStockSymbolInDateRange(
            String symbol,
            Date startDate,
            Date endDate
    ){
        return tradeRepository.findHighestAndLowestPriceByStockSymbolInDateRange(symbol, startDate, endDate);
    }
}
