package com.hackerrank.stocktrade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;
import com.hackerrank.stocktrade.model.response.SearchTradesInDateRangeResponse;
import com.hackerrank.stocktrade.model.response.SearchTradesWithLowestAndHighestPriceQueryResponse;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class StocksService {

    private final TradeRepository tradeRepository;

    @Autowired
    public StocksService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public SearchStocksQueryResponse findHighestAndLowestPriceByNonExistingStockSymbolInDateRange(String stockSymbol,
                                                                                                  Date startDate,
                                                                                                  Date endDate){

        List<Trade> trades = this.findByStockSymbol(stockSymbol);

        if(trades != null && !trades.isEmpty()) {
            List<Trade> tradesInDateRange = this.searchTradesInDateRange(trades, startDate, endDate);

            SearchTradesWithLowestAndHighestPriceQueryResponse
                    searchTradesWithLowestAndHighestPriceQuery = this.searchTradesWithLowestAndHighestPriceQuery(tradesInDateRange);

            Trade tradeWithLowestPrice = searchTradesWithLowestAndHighestPriceQuery.getTradeWithLowestPrice();
            Trade tradeWithHighestPrice = searchTradesWithLowestAndHighestPriceQuery.getTradeWithHighestPrice();

            if( tradeWithLowestPrice != null && tradeWithHighestPrice != null ) {

                return new SearchStocksQueryResponse(tradeWithLowestPrice.getSymbol(),
                        tradeWithHighestPrice.getPrice(), tradeWithLowestPrice.getPrice());
            } else return null;

        } return null;

    }

    public SearchTradesWithLowestAndHighestPriceQueryResponse searchTradesWithLowestAndHighestPriceQuery(List<Trade> trades) {
        SearchTradesWithLowestAndHighestPriceQueryResponse response = new SearchTradesWithLowestAndHighestPriceQueryResponse();

        trades.stream().max(Comparator.comparing(Trade::getPrice)).ifPresent(response::setTradeWithHighestPrice);
        trades.stream().min(Comparator.comparing(Trade::getPrice)).ifPresent(response::setTradeWithLowestPrice);

        return response;
    }

    public List<Trade> searchTradesInDateRange(
            List<Trade> trades,
            Date startDate,
            Date endDate) {
        return trades.stream().filter( trade ->
                        trade.getTimestamp().before(endDate) && trade.getTimestamp().after(startDate))
                .collect(Collectors.toList());
    }

    public List<Trade> findByStockSymbol(String symbol){
        return tradeRepository.findBySymbol(symbol);
    }
}
