package com.exchange;

import com.exchange.backend.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeApplication implements CommandLineRunner {
   /* private ExchangeApplication() {
        throw new AssertionError("Not instantiable");
    }*/

   /** The application logger */
   private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeApplication.class);

   @Autowired
   private CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
