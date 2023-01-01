package com.hackerrank.stocktrade;

import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;
import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {

    private final UserRepository userRepository;
    private final TradeRepository tradeRepository;

    @Autowired
    public Application(UserRepository userRepository, TradeRepository tradeRepository) {
        this.userRepository = userRepository;
        this.tradeRepository = tradeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @PostConstruct
    private void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
    }

    @PostConstruct
    private void initDatabase() throws IOException {

        userRepository.save( new User(1L, "Eugen"));
        userRepository.save( new User(2L, "Sydney"));
        userRepository.save( new User(3L, "Melbourne"));


        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"id\": 1, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"ZAYO\", \"shares\": 11, \"price\": 154.77, \"timestamp\": \"2016-12-28 11:44:37\"}";
        Trade trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 2, \"type\": \"buy\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"A\", \"shares\": 19, \"price\": 153.57, \"timestamp\": \"2016-12-28 13:15:52\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 3, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", \"shares\": 12, \"price\": 135.89, \"timestamp\": \"2016-12-28 13:18:18\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 4, \"type\": \"buy\", \"user\": {\"id\": 2, \"name\": \"Daniel Cortez\"}, \"symbol\": \"MMS\", \"shares\": 15, \"price\": 183.45, \"timestamp\": \"2016-12-28 15:15:50\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 5, \"type\": \"buy\", \"user\": {\"id\": 2, \"name\": \"Daniel Cortez\"}, \"symbol\": \"WEX\", \"shares\": 10, \"price\": 190.73, \"timestamp\": \"2016-12-29 09:05:23\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 6, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"ZAYO\", \"shares\": 30, \"price\": 137.86, \"timestamp\": \"2016-12-30 11:42:40\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 7, \"type\": \"buy\", \"user\": {\"id\": 2, \"name\": \"Daniel Cortez\"}, \"symbol\": \"MMS\", \"shares\": 19, \"price\": 183.45, \"timestamp\": \"2016-12-30 12:35:21\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 8, \"type\": \"buy\", \"user\": {\"id\": 4, \"name\": \"Derrick Garcia\"}, \"symbol\": \"WEX\", \"shares\": 11, \"price\": 172.35, \"timestamp\": \"2016-12-30 13:07:19\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 9, \"type\": \"buy\", \"user\": {\"id\": 3, \"name\": \"Connie Palmer\"}, \"symbol\": \"ZAYO\", \"shares\": 25, \"price\": 154.77, \"timestamp\": \"2016-12-30 13:36:20\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 10, \"type\": \"buy\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"MMS\", \"shares\": 28, \"price\": 152.93, \"timestamp\": \"2016-12-30 14:48:14\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 11, \"type\": \"buy\", \"user\": {\"id\": 3, \"name\": \"Connie Palmer\"}, \"symbol\": \"ZAYO\", \"shares\": 30, \"price\": 154.77, \"timestamp\": \"2016-12-31 09:59:16\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 12, \"type\": \"buy\", \"user\": {\"id\": 2, \"name\": \"Daniel Cortez\"}, \"symbol\": \"WEX\", \"shares\": 18, \"price\": 172.35, \"timestamp\": \"2016-12-31 12:01:56\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 13, \"type\": \"buy\", \"user\": {\"id\": 3, \"name\": \"Connie Palmer\"}, \"symbol\": \"A\", \"shares\": 22, \"price\": 135.89, \"timestamp\": \"2016-12-31 13:27:40\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 14, \"type\": \"buy\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"A\", \"shares\": 30, \"price\": 136.68, \"timestamp\": \"2016-12-31 15:29:15\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 15, \"type\": \"buy\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"MMS\", \"shares\": 29, \"price\": 168.67, \"timestamp\": \"2016-12-31 15:47:40\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 16, \"type\": \"sell\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"A\", \"shares\": 25, \"price\": 149.35, \"timestamp\": \"2017-01-03 11:59:32\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 17, \"type\": \"sell\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"MMS\", \"shares\": 25, \"price\": 182.01, \"timestamp\": \"2017-01-03 14:27:42\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 18, \"type\": \"sell\", \"user\": {\"id\": 1, \"name\": \"Jennifer Long\"}, \"symbol\": \"MMS\", \"shares\": 19, \"price\": 171.17, \"timestamp\": \"2017-01-05 15:43:00\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 19, \"type\": \"buy\", \"user\": {\"id\": 10, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
                "\"shares\": 11, \"price\": 149.35, \"timestamp\": \"2016-12-29 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 20, \"type\": \"buy\", \"user\": {\"id\": 11, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
                "\"shares\": 11, \"price\": 149.35, \"timestamp\": \"2017-01-03 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 21, \"type\": \"buy\", \"user\": {\"id\": 12, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
                "\"shares\": 11, \"price\": 135.89, \"timestamp\": \"2016-12-30 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 22, \"type\": \"buy\", \"user\": {\"id\": 13, \"name\": \"Derrick Garcia\"}, \"symbol\": \"MMS\", " +
                "\"shares\": 11, \"price\": 152.93, \"timestamp\": \"2016-12-30 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 23, \"type\": \"buy\", \"user\": {\"id\": 14, \"name\": \"Derrick Garcia\"}, \"symbol\": \"MMS\", " +
                "\"shares\": 11, \"price\": 183.45, \"timestamp\": \"2017-01-02 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 24, \"type\": \"buy\", \"user\": {\"id\": 15, \"name\": \"Derrick Garcia\"}, \"symbol\": \"WEXX\", " +
                "\"shares\": 11, \"price\": 172.35, \"timestamp\": \"2016-12-30 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );

        json = "{\"id\": 25, \"type\": \"buy\", \"user\": {\"id\": 16, \"name\": \"Derrick Garcia\"}, \"symbol\": \"ZAYO\", " +
                "\"shares\": 11, \"price\": 154.77, \"timestamp\": \"2025-12-28 11:44:37\"}";
        trade = objectMapper.readValue(json, Trade.class);
        tradeRepository.save( trade );



    }
}
