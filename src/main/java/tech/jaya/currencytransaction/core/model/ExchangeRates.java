package tech.jaya.currencytransaction.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExchangeRates {

    private boolean success;
    private Map<String, BigDecimal> rates;

    public boolean isInvalidRates(final List<String> ratesForCompare) {
        return rates.size() != 2 || !ratesForCompare.stream().allMatch(rates::containsKey);
    }
}
