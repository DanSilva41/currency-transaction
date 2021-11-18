package tech.jaya.currencytransaction.dataprovider.exchangerates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExchangeRatesResponse {

    private boolean success;
    private Map<String, BigDecimal> rates;
}
