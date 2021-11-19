package tech.jaya.currencytransaction.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FixtureConversionTransaction {

    public static Flux<ConversionTransaction> fluxList(final String userIdentifier) {
        return Flux.just(
                ConversionTransaction.builder()
                        .identifier(UUID.randomUUID().toString())
                        .userIdentifier(userIdentifier)
                        .originCurrency("BRL")
                        .destinationCurrency("USD")
                        .originValue(new BigDecimal("20"))
                        .destinationValue(new BigDecimal("3.62"))
                        .conversionRate(new BigDecimal("5.524861878"))
                        .transactionTime(LocalDateTime.now())
                        .build(),
                ConversionTransaction.builder()
                        .identifier(UUID.randomUUID().toString())
                        .userIdentifier(userIdentifier)
                        .originCurrency("EUR")
                        .destinationCurrency("USD")
                        .originValue(new BigDecimal("20"))
                        .destinationValue(new BigDecimal("22.68"))
                        .conversionRate(new BigDecimal("1.134"))
                        .transactionTime(LocalDateTime.now())
                        .build()
        );
    }

    public static ConversionTransaction one(final String userIdentifier) {
        return ConversionTransaction.builder()
                .identifier(UUID.randomUUID().toString())
                .userIdentifier(userIdentifier)
                .originCurrency("EUR")
                .destinationCurrency("USD")
                .originValue(new BigDecimal("20"))
                .destinationValue(new BigDecimal("22.68"))
                .conversionRate(new BigDecimal("1.134"))
                .transactionTime(LocalDateTime.now())
                .build();
    }

    public static ConversionTransaction oneWithoutSave(final String userIdentifier) {
        return ConversionTransaction.builder()
                .userIdentifier(userIdentifier)
                .originCurrency("EUR")
                .destinationCurrency("USD")
                .originValue(new BigDecimal("20"))
                .build();
    }

    public static ConversionTransaction oneWithSameCurrency(final String userIdentifier) {
        return ConversionTransaction.builder()
                .userIdentifier(userIdentifier)
                .originCurrency("BRL")
                .destinationCurrency("brl")
                .originValue(new BigDecimal("20"))
                .build();
    }

    public static List<ConversionTransaction> list(final String userIdentifier) {
        return Arrays.asList(ConversionTransaction.builder()
                        .identifier(UUID.randomUUID().toString())
                        .userIdentifier(userIdentifier)
                        .originCurrency("EUR")
                        .destinationCurrency("USD")
                        .originValue(new BigDecimal("20"))
                        .destinationValue(new BigDecimal("22.68"))
                        .conversionRate(new BigDecimal("1.134"))
                        .transactionTime(LocalDateTime.now())
                        .build(),
                ConversionTransaction.builder()
                        .identifier(UUID.randomUUID().toString())
                        .userIdentifier(userIdentifier)
                        .originCurrency("JPY")
                        .destinationCurrency("GBP")
                        .originValue(new BigDecimal("20"))
                        .destinationValue(new BigDecimal("22.68"))
                        .conversionRate(new BigDecimal("1.134"))
                        .transactionTime(LocalDateTime.now())
                        .build()
        );
    }
}
