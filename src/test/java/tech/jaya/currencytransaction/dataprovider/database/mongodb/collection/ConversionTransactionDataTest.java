package tech.jaya.currencytransaction.dataprovider.database.mongodb.collection;

import org.junit.jupiter.api.Test;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransactionData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConversionTransactionDataTest {

    @Test
    void shouldBeEquals() {
        final String generateIdentifier = UUID.randomUUID().toString();
        final var first = ConversionTransactionData.builder()
                .identifier(generateIdentifier)
                .build();
        final var second = ConversionTransactionData.builder()
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
        final var conversionTransactionData = FixtureConversionTransactionData.one();

        assertEquals(conversionTransactionData, conversionTransactionData);
        assertEquals(conversionTransactionData.hashCode(), conversionTransactionData.hashCode());
        assertEquals(conversionTransactionData.getIdentifier(), conversionTransactionData.getIdentifier());
    }

    @Test
    void shouldReturnFalseWhenNull() {
        final var account = ConversionTransactionData.builder().build();
        assertNotNull(account);
    }

    @Test
    void shouldReturnFalseWhenOtherObject() {
        final var conversion = ConversionTransactionData.builder().build();
        final var conversionCompared = ConversionTransactionData.builder().identifier(UUID.randomUUID().toString()).build();

        assertNotEquals(conversion, conversionCompared);
    }

    @Test
    void shouldReturnFalseWhenOtherType() {
        assertNotEquals(ConversionTransactionData.builder().build(), new Object());
    }
}