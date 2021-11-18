package tech.jaya.currencytransaction.dataprovider.exchangerates;

import org.junit.jupiter.api.Test;
import tech.jaya.currencytransaction.fixture.FixtureExchangeRatesResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExchangeRatesResponseConverterTest {

    @Test
    void shouldConvertToConversionTransactionWithSuccess() {
        var exchangeRatesResponse = FixtureExchangeRatesResponse.withDefaultValues();
        var exchangeRates = ExchangeRatesResponseConverter.toExchangeRates(exchangeRatesResponse);
        exchangeRates.doOnNext(c -> {
            assertEquals(c.isSuccess(), exchangeRatesResponse.isSuccess());
            assertEquals(c.getRates(), exchangeRatesResponse.getRates());
        }).block();
    }

}