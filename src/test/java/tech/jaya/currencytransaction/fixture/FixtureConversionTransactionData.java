package tech.jaya.currencytransaction.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.jaya.currencytransaction.dataprovider.database.mongodb.collection.ConversionTransactionData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FixtureConversionTransactionData {

    public static ConversionTransactionData one() {
        return ConversionTransactionData.builder()
                .identifier(UUID.randomUUID().toString())
                .userIdentifier("1")
                .originCurrency("EUR")
                .destinationCurrency("USD")
                .originValue(new BigDecimal("20"))
                .destinationValue(new BigDecimal("22.68"))
                .conversionRate(new BigDecimal("1.134"))
                .transactionTime(LocalDateTime.now())
                .build();
    }
}
