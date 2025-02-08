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
        if( trade.getId() != null ) {
            if(!tradeRepository.existsById(trade.getId()))
                return this.tradeRepository.save(trade);
            return null;
        }
        return null;
    }

    public List<Trade> findAllTrades() {
        return this.tradeRepository.findAll();
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
        return this.tradeRepository.findBySymbolAndType(symbol, type);
    }
}
