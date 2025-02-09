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
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
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
    public ResponseEntity<?> stock(@PathVariable(name = "symbol") String stockSymbol,
                                                                 @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                 @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws IOException {



        String jsonResponse = "{\"message\": \"There are no trades in the given date range\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Object response = objectMapper.readValue(jsonResponse, Object.class);

        Optional<SearchStocksQueryResponse> optionalSearchStocksQueryResponse = this.stocksService.
               findHighestAndLowestPriceByNonExistingStockSymbolInDateRange(
                       stockSymbol,
                       startDate,
                       endDate);

       if(optionalSearchStocksQueryResponse.isPresent()) {
           SearchStocksQueryResponse searchStocksQueryResponse = optionalSearchStocksQueryResponse.get();
           if( searchStocksQueryResponse.getSymbol() == null) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
           }
           return new ResponseEntity<>(searchStocksQueryResponse, HttpStatus.OK);
       } else return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
