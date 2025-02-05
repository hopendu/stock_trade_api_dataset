package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    private final ResourcesService resourcesService;

    @Autowired
    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @DeleteMapping
    public ResponseEntity<List<Trade>> eraseAllTrades() {
        List<Trade> erasedTrades = resourcesService.eraseAllTradesRecordsIncludingUserRecords();
        return ResponseEntity.ok(erasedTrades);
    }
}
