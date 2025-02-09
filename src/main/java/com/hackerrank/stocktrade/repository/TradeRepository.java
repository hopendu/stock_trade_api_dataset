package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.custom_query.TradeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> , TradeRepositoryCustom, JpaSpecificationExecutor<Trade> {
    List<Trade> findAllBySymbol(String symbol);
    List<Trade> findAllByUserId(Long userId);
}
