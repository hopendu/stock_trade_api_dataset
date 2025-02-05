package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    private final TradeService tradeService;

    @Autowired
    public TradesController(TradeService tradeService) {
        this.tradeService = tradeService;
    }


    @PostMapping
    public ResponseEntity<Trade> addTrade(@RequestBody Trade trade) {
         Trade createdTrade = tradeService.addTrade(trade);
         return new ResponseEntity<>(createdTrade, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Trade>> getTrades() {
        List<Trade> allTrades = this.tradeService.findAllTrades();
        return new ResponseEntity<>(allTrades, HttpStatus.OK);
    }
    
}
