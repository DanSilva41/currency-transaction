package tech.jaya.currencytransaction.core.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class CalculateCurrenciesConversion {

    private static final int LARGE_SCALE = 6;
    private static final int SHORT_SCALE = 2;
    
    protected BigDecimal convertValue(final BigDecimal originValue, final BigDecimal rateOfOriginCurrency,
                                      final BigDecimal rateOfDestinationCurrency) {
        final var valueInBaseCurrency = originValue.multiply(BigDecimal.ONE.divide(rateOfOriginCurrency, LARGE_SCALE, RoundingMode.FLOOR));
        return valueInBaseCurrency.multiply(rateOfDestinationCurrency).setScale(SHORT_SCALE, RoundingMode.FLOOR);
    }

    protected BigDecimal getConversionRate(final BigDecimal originValue, final BigDecimal destinationValue) {
        return destinationValue.divide(originValue, LARGE_SCALE, RoundingMode.HALF_UP);
    }
}
