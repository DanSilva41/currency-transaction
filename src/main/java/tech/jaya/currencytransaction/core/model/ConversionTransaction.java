package tech.jaya.currencytransaction.core.model;

import lombok.*;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
        if (conversionHasAlreadyTakenPlace())
            throw new BaseBusinessException(ErrorMessage.CONVERSION_ALREADY_TAKEN_PLACE);
        this.destinationValue = super.convertValue(originValue, rateOfOriginCurrency, rateOfDestinationCurrency);
        this.conversionRate = super.getConversionRate(originValue, destinationValue);
        this.transactionTime = LocalDateTime.now(ZoneOffset.UTC);
    }

    private boolean conversionHasAlreadyTakenPlace() {
        return Objects.nonNull(destinationValue)
                || Objects.nonNull(conversionRate)
                || Objects.nonNull(transactionTime);
    }

    public boolean verifyIfOriginAndDestinationCurrencySame() {
        return this.originCurrency.equalsIgnoreCase(destinationCurrency);
    }
}
