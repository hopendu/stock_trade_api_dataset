package com.hackerrank.stocktrade.repository.custom_query.impl;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.response.SearchStocksQueryResponse;
import com.hackerrank.stocktrade.repository.custom_query.TradeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TradeRepositoryCustomImpl implements TradeRepositoryCustom{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<SearchStocksQueryResponse> findHighestAndLowestPriceByStockSymbolInDateRange(String symbol, Date startDate, Date endDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SearchStocksQueryResponse> query = cb.createQuery(SearchStocksQueryResponse.class);
        Root<Trade> root = query.from(Trade.class);
        List<Predicate> predicates = new ArrayList<>();

        if (symbol != null && !symbol.isEmpty() && startDate != null && endDate != null) {

            predicates.add(cb.equal(root.get("symbol"), symbol));
            predicates.add(cb.greaterThanOrEqualTo(root.get("timestamp"), startDate));
            predicates.add(cb.lessThanOrEqualTo(root.get("timestamp"), endDate));

            query.where(predicates.toArray(new Predicate[0]));

            return entityManager.createQuery(query).getResultList();

        } else throw new IllegalArgumentException("symbol, startDate or endDate cannot be null");
    }
}
