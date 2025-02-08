package com.hackerrank.stocktrade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final StocksService stocksService;

    @Autowired
    public TradeService(TradeRepository tradeRepository, StocksService stocksService){
        this.tradeRepository = tradeRepository;
        this.stocksService = stocksService;
    }

    public Trade addTrade(Trade trade) {
        return this.tradeRepository.save(trade);
    }

    public List<Trade> findAllTrades() {
        List<Trade> trades = new ArrayList<>();
        this.tradeRepository.findAll().forEach(trades::add);
        return trades;
    }

    public List<Trade> findAllTradeByStockSymbolAndTradeTypeInDateRange(String stockSymbol, String tradeType, Date startDate, Date endDate) throws IOException {

        List<Trade> trades = this.findAllByStockSymbolAndTradeType(stockSymbol, tradeType);

        if(trades != null && !trades.isEmpty()) {
            List<Trade> filteredTrades = stocksService.searchTradesInDateRange(trades, startDate, endDate);
            if(filteredTrades != null && !filteredTrades.isEmpty()) {
                return filteredTrades;
            } else return null;
        }

        return null;

    }

    public List<Trade> findAllByStockSymbolAndTradeType(String symbol, String type) throws IOException {
        //          TEST
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String json = "{\"id\": 1, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
//                "\"shares\": 11, \"price\": 154.77, \"timestamp\": \"2016-12-28 11:44:37\"}";
//        Trade trade1 = objectMapper.readValue(json, Trade.class);
//
//        String json2 = "{\"id\": 2, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
//                "\"shares\": 11, \"price\": 200.77, \"timestamp\": \"2016-12-28 11:44:37\"}";
//        Trade trade2 = objectMapper.readValue(json, Trade.class);
//
//        String json3 = "{\"id\": 3, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
//                "\"shares\": 11, \"price\": 500.77, \"timestamp\": \"2016-12-28 11:44:37\"}";
//        Trade trade3 = objectMapper.readValue(json, Trade.class);
//
//
//        return Arrays.stream(new Trade[]{trade1, trade3, trade2}).
//                collect(Collectors.toList());
        return this.tradeRepository.findBySymbolAndType(symbol, type);
    }
}
