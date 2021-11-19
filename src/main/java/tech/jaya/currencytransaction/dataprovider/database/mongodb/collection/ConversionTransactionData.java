package tech.jaya.currencytransaction.dataprovider.database.mongodb.collection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
