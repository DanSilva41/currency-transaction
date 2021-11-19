package tech.jaya.currencytransaction.dataprovider.client.exchangerates;

import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;
import java.util.Map;
import reactor.core.publisher.Mono;

@Headers("Content-Type: application/json")
public interface ExchangeRatesClient {

    @RequestLine("GET /")
    Mono<ExchangeRatesResponse> getAllUsers(@QueryMap Map<String, Object> queryMap);
}
