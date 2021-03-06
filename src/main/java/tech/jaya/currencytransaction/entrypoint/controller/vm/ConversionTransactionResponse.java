package tech.jaya.currencytransaction.entrypoint.controller.vm;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConversionTransactionResponse {

    private String identifier;
    private String userIdentifier;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal originValue;
    private BigDecimal destinationValue;
    private BigDecimal conversionRate;
    private LocalDateTime transactionTime;
}
