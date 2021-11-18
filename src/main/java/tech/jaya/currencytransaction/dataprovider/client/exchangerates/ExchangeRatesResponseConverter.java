package tech.jaya.currencytransaction.dataprovider.client.exchangerates;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ExchangeRates;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeRatesResponseConverter {

    public static Mono<ExchangeRates> toExchangeRates(final ExchangeRatesResponse exchangeRatesResponse) {
        return Mono.just(ExchangeRates.builder()
                .success(exchangeRatesResponse.isSuccess())
                .rates(exchangeRatesResponse.getRates())
                .build());
    }
}
