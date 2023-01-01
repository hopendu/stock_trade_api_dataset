package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;
import com.hackerrank.stocktrade.model.response.SearchTradesWithLowestAndHighestPriceQueryResponse;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
public class StocksService {

    private final TradeRepository tradeRepository;

    @Autowired
    public StocksService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Optional<SearchStocksQueryResponse> findHighestAndLowestPriceByNonExistingStockSymbolInDateRange(String stockSymbol,
                                                                                                  Date startDate,
                                                                                                  Date endDate) throws IOException {
        Optional<SearchStocksQueryResponse> optionalSearchStocksQueryResponse = Optional.empty();

        List<Trade> trades = this.findByStockSymbol(stockSymbol);

        if (trades.isEmpty()) return optionalSearchStocksQueryResponse;

        List<Trade> tradesInDateRange = this.searchTradesInDateRange(trades, startDate, endDate);

        if (tradesInDateRange.isEmpty()) return Optional.of(new SearchStocksQueryResponse());

        List<Trade>  tradesWithStockSymbolInDateRange = tradesInDateRange.stream()
                .filter(trade -> trade.getSymbol().equals(stockSymbol))
                .collect(Collectors.toList());

        SearchTradesWithLowestAndHighestPriceQueryResponse
                searchTradesWithLowestAndHighestPriceQuery = this.searchTradesWithLowestAndHighestPriceQuery(tradesWithStockSymbolInDateRange);

        Trade tradeWithLowestPrice = searchTradesWithLowestAndHighestPriceQuery.getTradeWithLowestPrice();
        Trade tradeWithHighestPrice = searchTradesWithLowestAndHighestPriceQuery.getTradeWithHighestPrice();

        if( tradeWithLowestPrice != null && tradeWithHighestPrice != null )
            optionalSearchStocksQueryResponse = Optional.of(new SearchStocksQueryResponse(stockSymbol,
                    tradeWithHighestPrice.getPrice(), tradeWithLowestPrice.getPrice()));

        return optionalSearchStocksQueryResponse;

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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = format.format(startDate);
        String end = format.format(endDate);
        return trades.stream().filter( trade ->
                        trade.getTimestamp().before(endDate) && trade.getTimestamp().after(startDate)
                || trade.getTimestamp().toString().contains(end)
                || trade.getTimestamp().toString().contains(start))
                .collect(Collectors.toList());
    }

    public List<Trade> findByStockSymbol(String symbol) {
            return tradeRepository.findAllBySymbol(symbol);
    }
}
