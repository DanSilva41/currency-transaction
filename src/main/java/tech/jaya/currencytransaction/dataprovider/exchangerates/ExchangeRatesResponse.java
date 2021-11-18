package tech.jaya.currencytransaction.dataprovider.exchangerates;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public class ExchangeRatesResponse {

    private boolean success;
    private Map<String, BigDecimal> rates;
}
