package com.hackerrank.stocktrade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final StocksService stocksService;
    private final UserRepository userRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository, StocksService stocksService, UserRepository userRepository){
        this.tradeRepository = tradeRepository;
        this.stocksService = stocksService;
        this.userRepository = userRepository;
    }

    public Optional<Trade> addTrade(Trade trade) {
        Optional<Trade> optionalTrade = Optional.empty();

        if( trade == null) return optionalTrade;

        Long id = trade.getId();

        if( id == null) return Optional.of(tradeRepository.save(trade));

        boolean tradeExists = tradeRepository.existsById(id);

        if(!tradeExists) {
            optionalTrade = Optional.of(tradeRepository.save(trade));
        }

        return optionalTrade;
    }

    public Optional<List<Trade>> findAllTrades() {
        Optional<List<Trade>> optionalTrades = Optional.empty();

        List<Trade> trades = tradeRepository.findAll();

        return trades.isEmpty() ? optionalTrades : Optional.of(trades);
    }

    public Optional<List<Trade> > findAllTradeByStockSymbolAndTradeTypeInDateRange(String stockSymbol,
                                                                                   String tradeType,
                                                                                   Date startDate,
                                                                                   Date endDate) throws IOException {

        Optional<List<Trade>> optionalTrades = Optional.empty();

        List<Trade> trades = this.findAllByStockSymbolAndTradeType(stockSymbol, tradeType);

        if(trades.isEmpty()) return Optional.of(new ArrayList<>());

        List<Trade> filteredTrades = stocksService.searchTradesInDateRange(trades, startDate, endDate);

        if(filteredTrades.isEmpty()) return optionalTrades;

        return Optional.of(
                filteredTrades.stream()
                        .filter( trade -> trade.getSymbol().equals(stockSymbol) &&
                                trade.getType().equals(tradeType))
                        .collect(Collectors.toList())
        );

    }

    public List<Trade> findAllByStockSymbolAndTradeType(String symbol, String type) throws IOException {
        List<Trade> tradesByStockSymbol = this.tradeRepository.findAllBySymbol(symbol);
        return tradesByStockSymbol.stream()
                .filter( trade -> trade.getType().equals(type))
                .collect(Collectors.toList());
    }

    public Optional<Trade> findTradeById(Long id) {
        return this.tradeRepository.findById(id);
    }

    public Optional<List<Trade>> findTradeByUser(Long id){
        Optional<List<Trade>> optionalTrade = Optional.empty();
        List<Trade> trades = this.tradeRepository.findAllByUserId(id);
        return  trades.isEmpty() ? optionalTrade : Optional.of(trades);
    }
}
