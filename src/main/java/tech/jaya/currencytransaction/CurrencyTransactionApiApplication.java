package tech.jaya.currencytransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveMongoRepositories(basePackages = "tech.jaya.currencytransaction.dataprovider.database.mongodb")
@EnableReactiveFeignClients(basePackages = "tech.jaya.currencytransaction.dataprovider.exchangerates")
@SpringBootApplication
public class CurrencyTransactionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyTransactionApiApplication.class, args);
    }

}
