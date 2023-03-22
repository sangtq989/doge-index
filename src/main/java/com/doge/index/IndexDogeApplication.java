package com.doge.index;

import com.doge.index.config.*;
import com.doge.index.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.*;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(DogeConfigProperties.class)
public class IndexDogeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndexDogeApplication.class, args);
    }


}
