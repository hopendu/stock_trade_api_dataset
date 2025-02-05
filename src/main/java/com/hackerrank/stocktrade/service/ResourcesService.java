package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.hibernate.validator.constraintvalidators.RegexpURLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesService {

    private final TradeRepository tradeRepository;

    @Autowired
    public ResourcesService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> eraseAllTradesRecordsIncludingUserRecords() {
        List<Trade> trades = tradeRepository.findAll();
        tradeRepository.deleteAll();
        List<Trade> erasedTrades = tradeRepository.findAll();
        if(erasedTrades.isEmpty()) {
            return trades;
        } else throw new RuntimeException("Could not erase all trades records");
    }
}
