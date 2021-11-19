package tech.jaya.currencytransaction.dataprovider.database.mongodb.converter;

import org.junit.jupiter.api.Test;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransactionData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionTransactionDataConverterTest {

    @Test
    void shouldConvertToConversionTransactionWithSuccess() {
        final var userIdentifier = "1";
        var conversionTransactionData = FixtureConversionTransactionData.one();
        var conversionTransaction = ConversionTransactionDataConverter.toConversionTransaction(conversionTransactionData);
        conversionTransaction.doOnNext(c -> {
            assertEquals(c.getUserIdentifier(), userIdentifier);
            assertEquals(c.getOriginCurrency(), conversionTransactionData.getOriginCurrency());
            assertEquals(c.getDestinationCurrency(), conversionTransactionData.getDestinationCurrency());
            assertEquals(c.getOriginValue(), conversionTransactionData.getOriginValue());
            assertEquals(c.getDestinationValue(), conversionTransactionData.getDestinationValue());
            assertEquals(c.getConversionRate(), conversionTransactionData.getConversionRate());
            assertEquals(c.getTransactionTime(), conversionTransactionData.getTransactionTime());
        }).block();
    }

    @Test
    void shouldConvertToConversionTransactionDataWithSuccess() {
        final var userIdentifier = "1";
        var conversionTransaction = FixtureConversionTransaction.one(userIdentifier);
        var conversionTransactionData = ConversionTransactionDataConverter.toConversionTransactionData(conversionTransaction);
        assertEquals(conversionTransactionData.getUserIdentifier(), userIdentifier);
        assertEquals(conversionTransactionData.getOriginCurrency(), conversionTransaction.getOriginCurrency());
        assertEquals(conversionTransactionData.getDestinationCurrency(), conversionTransaction.getDestinationCurrency());
        assertEquals(conversionTransactionData.getOriginValue(), conversionTransaction.getOriginValue());
        assertEquals(conversionTransactionData.getDestinationValue(), conversionTransaction.getDestinationValue());
        assertEquals(conversionTransactionData.getConversionRate(), conversionTransaction.getConversionRate());
        assertEquals(conversionTransactionData.getTransactionTime(), conversionTransaction.getTransactionTime());
    }
}