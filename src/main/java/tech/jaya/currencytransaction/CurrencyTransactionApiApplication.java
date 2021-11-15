package tech.jaya.currencytransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class CurrencyTransactionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyTransactionApiApplication.class, args);
    }

}
