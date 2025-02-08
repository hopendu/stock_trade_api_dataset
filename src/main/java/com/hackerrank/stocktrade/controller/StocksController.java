package com.hackerrank.stocktrade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;
import com.hackerrank.stocktrade.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

    private final StocksService stocksService;

    @Autowired
    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;

    }
    @GetMapping("/{symbol}/price")
    public ResponseEntity<SearchStocksQueryResponse> stock(@PathVariable(name = "symbol") String stockSymbol,
                                                                 @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                 @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws IOException {

       SearchStocksQueryResponse searchStocksQueryResponse = this.stocksService.
               findHighestAndLowestPriceByNonExistingStockSymbolInDateRange(
                       stockSymbol,
                       startDate,
                       endDate);

       if(searchStocksQueryResponse != null) {
           return new ResponseEntity<>(searchStocksQueryResponse, HttpStatus.OK);
       } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
