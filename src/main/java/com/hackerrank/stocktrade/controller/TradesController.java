package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
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
         if( createdTrade != null ) {
             return new ResponseEntity<>(createdTrade, HttpStatus.CREATED);
         }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Trade>> getTrades() {
        List<Trade> allTrades = this.tradeService.findAllTrades();
        return new ResponseEntity<>(allTrades, HttpStatus.OK);
    }

    @GetMapping("/stocks/{symbol}")
    public ResponseEntity<List<Trade>> getTradeBySymbolAndTradeTypeInDateRange(@PathVariable String symbol,
                                                                         @RequestParam(name = "type") String tradeType,
                                                                         @RequestParam(name = "start") @DateTimeFormat( pattern = "yyyy-MM-dd") Date startDate,
                                                                         @RequestParam(name = "end") @DateTimeFormat( pattern = "yyyy-MM-dd") Date endDate) throws IOException {

        List<Trade> allTrades = this.tradeService.findAllTradeByStockSymbolAndTradeTypeInDateRange(symbol, tradeType, startDate, endDate);
        if(allTrades != null && !allTrades.isEmpty()) {
            return new ResponseEntity<>(allTrades, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
