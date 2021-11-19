package tech.jaya.currencytransaction.dataprovider.client.exchangerates;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactivefeign.webclient.WebReactiveFeign;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;
import tech.jaya.currencytransaction.core.model.ExchangeRates;
import tech.jaya.currencytransaction.core.port.gateway.CurrencyLayer;

@Slf4j
@Component
public class ExchangeRatesProvider implements CurrencyLayer {

    @Value("${app.client.exchangeratesapi.url}")
    private String exchangeRatesURL;

    @Value("${app.client.exchangeratesapi.accessKey}")
    private String exchangeRatesAccessKey;

    public Mono<ExchangeRates> getConversionRates(final String originCurrency, final String destinationCurrency) {
        log.debug("Provider | ExchangeRates | getConversionRates");
        return WebReactiveFeign.<ExchangeRatesClient>builder()
                .fallback(e -> {
                    log.error("Provider | ExchangeRates | Error when execute :getConversionRates: | {}", e);
                    return Mono.error(new BaseBusinessException(ErrorMessage.FAIL_GET_EXCHANGE_RATES));
                })
                .target(ExchangeRatesClient.class, exchangeRatesURL)
                .getAllUsers(getQueryParamsExchangeWithSymbols(originCurrency, destinationCurrency))
                .flatMap(ExchangeRatesResponseConverter::toExchangeRates);
    }

    public Map<String, Object> getQueryParamsExchangeWithSymbols(final String originCurrency, final String destinationCurrency) {
        final var queryParamsExchangeWithSymbols = getDefaultQueryParamsExchange();
        queryParamsExchangeWithSymbols.put(ExchangeParameter.SYMBOLS_NAME_PARAMETER, String.join(",", originCurrency, destinationCurrency));
        return queryParamsExchangeWithSymbols;
    }

    public Map<String, Object> getDefaultQueryParamsExchange() {
        final Map<String, Object> queryParamsExchange = new HashMap<>();
        queryParamsExchange.put(ExchangeParameter.ACCESS_KEY_NAME_PARAMETER, exchangeRatesAccessKey);
        return queryParamsExchange;
    }
}
