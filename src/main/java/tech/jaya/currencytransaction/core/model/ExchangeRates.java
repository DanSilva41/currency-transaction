package tech.jaya.currencytransaction.core.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExchangeRates {

    private boolean success;
    private Map<String, BigDecimal> rates;

    public boolean isInvalidRates(final Set<String> ratesForCompare) {
        return Objects.isNull(rates)
                || rates.size() != ratesForCompare.size()
                || !ratesForCompare.stream().allMatch(rates::containsKey);
    }
}
