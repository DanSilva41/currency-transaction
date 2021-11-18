package tech.jaya.currencytransaction.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExchangeRates {

    private boolean success;
    private Map<String, BigDecimal> rates;

    public boolean isInvalidRates(final Set<String> ratesForCompare) {
        return rates.size() != 2 || !ratesForCompare.stream().allMatch(rates::containsKey);
    }
}
