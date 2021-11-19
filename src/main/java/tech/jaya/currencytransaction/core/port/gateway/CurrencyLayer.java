package tech.jaya.currencytransaction.core.port.gateway;

import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ExchangeRates;

public interface CurrencyLayer {

    Mono<ExchangeRates> getConversionRates(String originCurrency, String destinationCurrency);
}
