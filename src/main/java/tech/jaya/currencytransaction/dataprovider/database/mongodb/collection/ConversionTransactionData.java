package tech.jaya.currencytransaction.dataprovider.database.mongodb.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Document
public class ConversionTransactionData {

    @EqualsAndHashCode.Include
    @Id
    private String identifier;
    private String userIdentifier;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal originValue;
    private BigDecimal destinationValue;
    private BigDecimal conversionRate;
    private LocalDateTime transactionTime;
}
