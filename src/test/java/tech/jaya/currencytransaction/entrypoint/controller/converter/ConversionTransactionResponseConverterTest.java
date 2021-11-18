package tech.jaya.currencytransaction.entrypoint.controller.converter;

import org.junit.jupiter.api.Test;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;

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