package tech.jaya.currencytransaction.entrypoint.controller.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversionTransactionRequestConverter {

    public static Mono<ConversionTransaction> toConversionTransaction(final String userId, final ConversionTransactionRequest request) {
        return Mono.just(ConversionTransaction.builder()
                .userIdentifier(userId)
                .originCurrency(request.getOriginCurrency())
                .destinationCurrency(request.getDestinationCurrency())
                .originValue(request.getSourceValue())
                .build());
    }
}
