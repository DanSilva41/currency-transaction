package tech.jaya.currencytransaction.entrypoint.controller.converter;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionRequest;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;

@Component
public class ConversionTransactionRequestConverter {

    public Mono<ConversionTransaction> toConversionTransaction(final String userId, final ConversionTransactionRequest request) {
        return Mono.just(ConversionTransaction.builder()
                .userIdentifier(userId)
                .originCurrency(request.getOriginCurrency())
                .destinationCurrency(request.getDestinationCurrency())
                .originValue(request.getSourceValue())
                .build());
    }
}
