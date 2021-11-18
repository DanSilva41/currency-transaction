package tech.jaya.currencytransaction.entrypoint.controller.converter;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionResponse;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;

@Component
public class ConversionTransactionResponseConverter {

    public Mono<ConversionTransactionResponse> toConversionTransactionResponse(final ConversionTransaction conversionTransaction) {
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
