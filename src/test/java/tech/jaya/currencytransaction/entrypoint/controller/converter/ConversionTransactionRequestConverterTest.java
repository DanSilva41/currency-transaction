package tech.jaya.currencytransaction.entrypoint.controller.converter;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransactionRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionTransactionRequestConverterTest {

    @Test
    void shouldConvertToConversionTransactionWithSuccess() {
        final var userIdentifier = "1";
        var oneRequest = FixtureConversionTransactionRequest.withDefaultValues();
        Mono<ConversionTransaction> conversionTransaction = ConversionTransactionRequestConverter.toConversionTransaction(userIdentifier, oneRequest);
        conversionTransaction.doOnNext(c -> {
            assertEquals(c.getUserIdentifier(), userIdentifier);
            assertEquals(c.getOriginCurrency(), oneRequest.getOriginCurrency());
            assertEquals(c.getDestinationCurrency(), oneRequest.getDestinationCurrency());
            assertEquals(c.getOriginValue(), oneRequest.getSourceValue());
        }).block();
    }

}