package tech.jaya.currencytransaction.core.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class CalculateCurrenciesConversion {

    protected BigDecimal convertValue(final BigDecimal originValue, final BigDecimal rateOfOriginCurrency,
                                      final BigDecimal rateOfDestinationCurrency) {
        var valueInBaseCurrency = originValue.multiply(BigDecimal.ONE.divide(rateOfOriginCurrency, 6, RoundingMode.FLOOR));
        return valueInBaseCurrency.multiply(rateOfDestinationCurrency).setScale(2, RoundingMode.FLOOR);
    }

    protected BigDecimal getConversionRate(final BigDecimal originValue, final BigDecimal destinationValue) {
        return destinationValue.divide(originValue, 6, RoundingMode.HALF_UP);
    }
}
