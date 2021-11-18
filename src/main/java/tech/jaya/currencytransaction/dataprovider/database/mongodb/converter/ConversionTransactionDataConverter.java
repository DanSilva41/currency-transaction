package tech.jaya.currencytransaction.dataprovider.database.mongodb.converter;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.dataprovider.database.mongodb.collection.ConversionTransactionData;

@Component
public class ConversionTransactionDataConverter {

    public Mono<ConversionTransaction> toConversionTransaction(ConversionTransactionData source) {
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

    public ConversionTransactionData toConversionTransactionData(ConversionTransaction source) {
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
