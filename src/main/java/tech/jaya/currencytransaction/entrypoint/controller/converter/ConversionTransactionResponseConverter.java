package tech.jaya.currencytransaction.entrypoint.controller.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversionTransactionResponseConverter {

    public static Mono<ConversionTransactionResponse> toConversionTransactionResponse(final ConversionTransaction conversionTransaction) {
        return Mono.just(ConversionTransactionResponse.builder()
                .identifier(conversionTransaction.getIdentifier())
                .userIdentifier(conversionTransaction.getUserIdentifier())
                .originCurrency(conversionTransaction.getOriginCurrency())
                .destinationCurrency(conversionTransaction.getDestinationCurrency())
                .originValue(conversionTransaction.getOriginValue())
                .destinationValue(conversionTransaction.getDestinationValue())
                .conversionRate(conversionTransaction.getConversionRate())
                .transactionTime(conversionTransaction.getTransactionTime())
                .build());
    }
}
