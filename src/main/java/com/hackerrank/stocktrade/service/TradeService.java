package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository){
        this.tradeRepository = tradeRepository;
    }

    public Trade addTrade(Trade trade) {
        return this.tradeRepository.save(trade);
    }

    public List<Trade> findAllTrades() {
        List<Trade> trades = new ArrayList<>();
        this.tradeRepository.findAll().forEach(trades::add);
        return trades;
    }
}
