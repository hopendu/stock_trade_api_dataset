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

        tradeRepository.deleteAll();

        String json0 = "{\"id\": 10, \"type\": \"buy\", \"user\": {\"id\": 10, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
                "\"shares\": 11, \"price\": 149.35, \"timestamp\": \"2016-12-29 11:44:37\"}";
        Trade trade0 = objectMapper.readValue(json0, Trade.class);
        tradeRepository.save( trade0 );

        String json1 = "{\"id\": 11, \"type\": \"buy\", \"user\": {\"id\": 11, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
                "\"shares\": 11, \"price\": 149.35, \"timestamp\": \"2017-01-03 11:44:37\"}";
        Trade trade1 = objectMapper.readValue(json1, Trade.class);
        tradeRepository.save( trade1 );

        String json2 = "{\"id\": 12, \"type\": \"buy\", \"user\": {\"id\": 12, \"name\": \"Derrick Garcia\"}, \"symbol\": \"A\", " +
                "\"shares\": 11, \"price\": 135.89, \"timestamp\": \"2016-12-30 11:44:37\"}";
        Trade trade2 = objectMapper.readValue(json2, Trade.class);
        tradeRepository.save( trade2 );

        String json3 = "{\"id\": 13, \"type\": \"buy\", \"user\": {\"id\": 13, \"name\": \"Derrick Garcia\"}, \"symbol\": \"MMS\", " +
                "\"shares\": 11, \"price\": 152.93, \"timestamp\": \"2016-12-30 11:44:37\"}";
        Trade trade3 = objectMapper.readValue(json3, Trade.class);
        tradeRepository.save( trade3 );

        String json4 = "{\"id\": 14, \"type\": \"buy\", \"user\": {\"id\": 14, \"name\": \"Derrick Garcia\"}, \"symbol\": \"MMS\", " +
                "\"shares\": 11, \"price\": 183.45, \"timestamp\": \"2017-01-02 11:44:37\"}";
        Trade trade4 = objectMapper.readValue(json4, Trade.class);
        tradeRepository.save( trade4 );

        String json5 = "{\"id\": 15, \"type\": \"buy\", \"user\": {\"id\": 15, \"name\": \"Derrick Garcia\"}, \"symbol\": \"WEX\", " +
                "\"shares\": 11, \"price\": 172.35, \"timestamp\": \"2016-12-30 11:44:37\"}";
        Trade trade5 = objectMapper.readValue(json5, Trade.class);
        tradeRepository.save( trade5 );

        String json6 = "{\"id\": 16, \"type\": \"buy\", \"user\": {\"id\": 16, \"name\": \"Derrick Garcia\"}, \"symbol\": \"ZAYO\", " +
                "\"shares\": 11, \"price\": 154.77, \"timestamp\": \"2025-12-28 11:44:37\"}";
        Trade trade6 = objectMapper.readValue(json6, Trade.class);
        tradeRepository.save( trade6 );



    }
}
