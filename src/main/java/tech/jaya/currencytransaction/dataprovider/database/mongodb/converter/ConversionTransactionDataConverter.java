package tech.jaya.currencytransaction.dataprovider.database.mongodb.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.dataprovider.database.mongodb.collection.ConversionTransactionData;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversionTransactionDataConverter {

    public static Mono<ConversionTransaction> toConversionTransaction(ConversionTransactionData source) {
        return Mono.just(ConversionTransaction.builder()
                .identifier(source.getIdentifier())
                .userIdentifier(source.getUserIdentifier())
                .originCurrency(source.getOriginCurrency())
                .destinationCurrency(source.getDestinationCurrency())
                .originValue(source.getOriginValue())
                .destinationValue(source.getDestinationValue())
                .conversionRate(source.getConversionRate())
                .transactionTime(source.getTransactionTime())
                .build());
    }

    public static ConversionTransactionData toConversionTransactionData(ConversionTransaction source) {
        return ConversionTransactionData.builder()
                .identifier(source.getIdentifier())
                .userIdentifier(source.getUserIdentifier())
                .originCurrency(source.getOriginCurrency())
                .destinationCurrency(source.getDestinationCurrency())
                .originValue(source.getOriginValue())
                .destinationValue(source.getDestinationValue())
                .conversionRate(source.getConversionRate())
                .transactionTime(source.getTransactionTime())
                .build();
    }
}
