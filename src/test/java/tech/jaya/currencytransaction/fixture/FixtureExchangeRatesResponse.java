package tech.jaya.currencytransaction.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.jaya.currencytransaction.dataprovider.exchangerates.ExchangeRatesResponse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FixtureExchangeRatesResponse {

    public static ExchangeRatesResponse withDefaultValues() {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("USD", new BigDecimal("1.134153"));
        rates.put("JPY", new BigDecimal("129.689864"));
        return new ExchangeRatesResponse(true, rates);
    }
}
