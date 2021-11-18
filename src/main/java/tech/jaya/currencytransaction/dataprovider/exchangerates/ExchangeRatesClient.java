package tech.jaya.currencytransaction.dataprovider.exchangerates;

import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;
import reactor.core.publisher.Mono;

import java.util.Map;

@Headers("Content-Type: application/json")
public interface ExchangeRatesClient {

    @RequestLine("GET /")
    Mono<ExchangeRatesResponse> getAllUsers(@QueryMap Map<String, Object> queryMap);
}
