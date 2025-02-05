package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.custom_query.TradeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> , TradeRepositoryCustom, JpaSpecificationExecutor<Trade> {
}
