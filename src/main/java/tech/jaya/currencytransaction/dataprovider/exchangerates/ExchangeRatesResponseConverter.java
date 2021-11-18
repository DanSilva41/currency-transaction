package tech.jaya.currencytransaction.dataprovider.exchangerates;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ExchangeRates;

@Component
public class ExchangeRatesResponseConverter {

    public Mono<ExchangeRates> toExchangeRates(final ExchangeRatesResponse exchangeRatesResponse) {
        return Mono.just(ExchangeRates.builder()
                .success(exchangeRatesResponse.isSuccess())
                .rates(exchangeRatesResponse.getRates())
                .build());
    }
}
