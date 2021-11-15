package tech.jaya.currencytransaction.controller.vm;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ConversionTransactionResponse {

    private String identifier;
    private String userIdentifier;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal originValue;
    private BigDecimal destinationValue;
    private BigDecimal conversionRate;
    private LocalDateTime transactionTime;

    public String getIdentifier() {
        return identifier;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public BigDecimal getOriginValue() {
        return originValue;
    }

    public BigDecimal getDestinationValue() {
        return destinationValue;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
}
