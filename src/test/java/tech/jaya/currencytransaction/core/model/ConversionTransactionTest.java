package tech.jaya.currencytransaction.core.model;

import org.junit.jupiter.api.Test;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConversionTransactionTest {

    @Test
    void shouldConvertCurrencyWithSuccess() {
        final var oneConversionTransaction = ConversionTransaction.builder()
                .identifier(UUID.randomUUID().toString())
                .userIdentifier("1")
                .originCurrency("BRL")
                .destinationCurrency("USD")
                .originValue(new BigDecimal("10"))
                .build();

        assertNull(oneConversionTransaction.getDestinationValue());
        assertNull(oneConversionTransaction.getConversionRate());
        assertNull(oneConversionTransaction.getTransactionTime());

        oneConversionTransaction.convertCurrencies(new BigDecimal("6.269056"), new BigDecimal("1.128929"));

        assertEquals(new BigDecimal("1.80"), oneConversionTransaction.getDestinationValue());
        assertEquals(new BigDecimal("0.180000"), oneConversionTransaction.getConversionRate());
        assertNotNull(oneConversionTransaction.getTransactionTime());
    }

    @Test
    void shouldThrowWhenDestinationValueFilledInExecuteConvert() {
        final var oneConversionTransaction = ConversionTransaction.builder()
                .identifier(UUID.randomUUID().toString())
                .userIdentifier("1")
                .originCurrency("BRL")
                .destinationCurrency("USD")
                .originValue(new BigDecimal("20"))
                .destinationValue(new BigDecimal("111.37"))
                .build();

        assertNotNull(oneConversionTransaction.getDestinationValue());
        assertNull(oneConversionTransaction.getConversionRate());
        assertNull(oneConversionTransaction.getTransactionTime());

        BaseBusinessException baseBusinessException = assertThrows(BaseBusinessException.class, () ->
                oneConversionTransaction.convertCurrencies(new BigDecimal("6.333688"), new BigDecimal("1.137314")));

        assertEquals(ErrorMessage.CONVERSION_ALREADY_TAKEN_PLACE, baseBusinessException.getErrorMessage());
    }

    @Test
    void shouldReturnThatOriginAndDestinationCurrencySame() {
        final var oneConversionTransaction = ConversionTransaction.builder()
                .identifier(UUID.randomUUID().toString())
                .userIdentifier("1")
                .originCurrency("USD")
                .destinationCurrency("usd")
                .originValue(new BigDecimal("20"))
                .build();

        assertTrue(oneConversionTransaction.verifyIfOriginAndDestinationCurrencySame());
    }

    @Test
    void shouldReturnThatOriginAndDestinationCurrencyNotSame() {
        final var oneConversionTransaction = ConversionTransaction.builder()
                .identifier(UUID.randomUUID().toString())
                .userIdentifier("1")
                .originCurrency("BRL")
                .destinationCurrency("USD")
                .originValue(new BigDecimal("20"))
                .build();

        assertFalse(oneConversionTransaction.verifyIfOriginAndDestinationCurrencySame());
    }

    @Test
    void shouldBeEquals() {
        final String generateIdentifier = UUID.randomUUID().toString();
        final var first = ConversionTransaction.builder()
                .identifier(generateIdentifier)
                .build();
        final var second = ConversionTransaction.builder()
                .identifier(generateIdentifier)
                .build();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertEquals(first.getIdentifier(), second.getIdentifier());
        assertNotEquals(0, first.hashCode());
        assertNotEquals(0, second.hashCode());
    }

    @Test
    void shouldBeEqualsWhenSameObject() {
        final var conversionTransactionData = FixtureConversionTransaction.one("1");

        assertEquals(conversionTransactionData, conversionTransactionData);
        assertEquals(conversionTransactionData.hashCode(), conversionTransactionData.hashCode());
        assertEquals(conversionTransactionData.getIdentifier(), conversionTransactionData.getIdentifier());
    }

    @Test
    void shouldReturnFalseWhenNull() {
        final var account = new ConversionTransaction();
        assertNotNull(account);
    }

    @Test
    void shouldReturnFalseWhenOtherObject() {
        final var conversion = ConversionTransaction.builder().build();
        final var conversionCompared = ConversionTransaction.builder().identifier(UUID.randomUUID().toString()).build();

        assertNotEquals(conversion, conversionCompared);
    }

    @Test
    void shouldReturnFalseWhenOtherType() {
        assertNotEquals(ConversionTransaction.builder().build(), new Object());
    }
}