package com.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeApplication {
    private ExchangeApplication() {
        throw new AssertionError("Not instantiable");
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }
}
