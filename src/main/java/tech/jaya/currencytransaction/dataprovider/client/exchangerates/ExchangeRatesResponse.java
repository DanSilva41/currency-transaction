package tech.jaya.currencytransaction.dataprovider.client.exchangerates;

import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExchangeRatesResponse {

    private boolean success;
    private Map<String, BigDecimal> rates;
}
