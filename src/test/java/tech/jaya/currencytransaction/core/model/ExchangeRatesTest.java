package tech.jaya.currencytransaction.core.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRatesTest {

    private static Set<String> ratesForCompare;

    @BeforeAll
    static void setUp() {
        ratesForCompare = Set.of("EUR", "BRL");
    }


    @Test
    void shouldInstantiate() {
        final var exchangeRates = new ExchangeRates();
        assertNotNull(exchangeRates);
    }

    @Test
    void shouldValidateWhenRatesIsNull() {
        final var exchangeRates = ExchangeRates.builder()
                .success(true)
                .rates(null)
                .build();

        assertTrue(exchangeRates.isInvalidRates(ratesForCompare));
    }

    @Test
    void shouldValidateWhenRatesIsEmpty() {
        final var exchangeRates = ExchangeRates.builder()
                .success(true)
                .rates(new HashMap<>())
                .build();

        assertTrue(exchangeRates.isInvalidRates(ratesForCompare));
    }

    @Test
    void shouldValidateWhenRatesIsLessThanTwo() {
        var rates = Map.of("EUR", new BigDecimal("0.88"));

        final var exchangeRates = ExchangeRates.builder()
                .success(true)
                .rates(rates)
                .build();

        assertTrue(exchangeRates.isInvalidRates(ratesForCompare));
    }

    @Test
    void shouldValidateWhenRatesIsGreaterThanTwo() {
        var rates = Map.of(
                "EUR", new BigDecimal("0.88"),
                "BRL", new BigDecimal("5.67"),
                "JPY", new BigDecimal("132.65")
        );

        final var exchangeRates = ExchangeRates.builder()
                .success(true)
                .rates(rates)
                .build();

        assertTrue(exchangeRates.isInvalidRates(ratesForCompare));
    }

    @Test
    void shouldValidateWhenRatesAgrees() {
        var rates = Map.of(
                "EUR", new BigDecimal("0.88"),
                "BRL", new BigDecimal("5.67")
        );

        final var exchangeRates = ExchangeRates.builder()
                .success(true)
                .rates(rates)
                .build();

        assertFalse(exchangeRates.isInvalidRates(ratesForCompare));
    }

}