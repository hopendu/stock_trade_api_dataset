package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;
import com.hackerrank.stocktrade.service.StocksService;
import com.hackerrank.stocktrade.util.DateStringToTimestampConvertor;
import com.hackerrank.stocktrade.util.abstraction.IDateStringToTimestampConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

    private final StocksService stocksService;

    @Autowired
    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;

    }
    @GetMapping("/{symbol}")
    public ResponseEntity<List<SearchStocksQueryResponse>> stock(@PathVariable String symbol,
                                                                 @RequestParam String startDate,
                                                                 @RequestParam String endDate) {

        IDateStringToTimestampConvertor convertor = new DateStringToTimestampConvertor("yyyy-MM-dd HH:mm:ss");

        Timestamp startTimestamp = convertor.convert(startDate);
        Timestamp endTimestamp = convertor.convert(endDate);

        List<SearchStocksQueryResponse> foundStocks = stocksService.findHighestAndLowestPriceByStockSymbolInDateRange(symbol, startTimestamp, endTimestamp);

        return new ResponseEntity<>(foundStocks, HttpStatus.OK);

    }
}
