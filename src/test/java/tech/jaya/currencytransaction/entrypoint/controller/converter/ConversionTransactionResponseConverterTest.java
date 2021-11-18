package tech.jaya.currencytransaction.entrypoint.controller.converter;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransactionRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionTransactionResponseConverterTest {

    @Test
    void shouldConvertToConversionTransactionResponseWithSuccess() {
        final var userIdentifier = "1";
        var conversionTransaction = FixtureConversionTransaction.one(userIdentifier);
        var response = ConversionTransactionResponseConverter.toConversionTransactionResponse(conversionTransaction);
        response.doOnNext(c -> {
            assertEquals(c.getUserIdentifier(), userIdentifier);
            assertEquals(c.getOriginCurrency(), conversionTransaction.getOriginCurrency());
            assertEquals(c.getDestinationCurrency(), conversionTransaction.getDestinationCurrency());
            assertEquals(c.getOriginValue(), conversionTransaction.getOriginValue());
            assertEquals(c.getDestinationValue(), conversionTransaction.getDestinationValue());
            assertEquals(c.getConversionRate(), conversionTransaction.getConversionRate());
            assertEquals(c.getTransactionTime(), conversionTransaction.getTransactionTime());
        }).block();
    }

}