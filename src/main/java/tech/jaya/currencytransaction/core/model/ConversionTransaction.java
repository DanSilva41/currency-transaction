package tech.jaya.currencytransaction.core.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ConversionTransaction extends CalculateCurrenciesConversion {

    @EqualsAndHashCode.Include
    @Builder.Default
    private String identifier = UUID.randomUUID().toString();
    private String userIdentifier;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal originValue;
    private BigDecimal destinationValue;
    private BigDecimal conversionRate;
    private LocalDateTime transactionTime;

    public void convertCurrencies(final BigDecimal rateOfOriginCurrency,
                                  final BigDecimal rateOfDestinationCurrency) {
        this.destinationValue = super.convertValue(originValue, rateOfOriginCurrency, rateOfDestinationCurrency);
        this.conversionRate = super.getConversionRate(originValue, destinationValue);
        this.transactionTime = LocalDateTime.now();
    }

    public boolean verifyIfOriginAndDestinationCurrencySame() {
        return this.originCurrency.equalsIgnoreCase(destinationCurrency);
    }
}
