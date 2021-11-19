package tech.jaya.currencytransaction.dataprovider.database.mongodb;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConversionTransactionRepositoryImplTest {

    @Autowired
    ConversionTransactionRepository repository;

    @Order(1)
    @Test
    void shouldGetAnyCurrenciesTransactionsByUser() {
        final var userIdentifier = "1";
        var allByUserIdentifierFlux = repository.findAllByUserIdentifier(userIdentifier);

        StepVerifier
                .create(allByUserIdentifierFlux)
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }

    @Order(2)
    @Test
    void shouldSaveConversionTransactionWithSuccess() {
        final var userIdentifier = "1";
        var oneConversionTransaction = FixtureConversionTransaction.one(userIdentifier);
        repository.save(oneConversionTransaction).block();

        var allByUserIdentifierFlux = repository.findAllByUserIdentifier(userIdentifier);

        StepVerifier
                .create(allByUserIdentifierFlux)
                .expectNextCount(0)
                .assertNext(c -> assertEquals(oneConversionTransaction.getIdentifier(), c.getIdentifier()))
                .expectComplete()
                .verify();
    }

    @Order(3)
    @Test
    void shouldGetCurrenciesTransactionsByUserWithSuccess() {
        final var userIdentifier = "2";
        var listConversionTransaction = FixtureConversionTransaction.list(userIdentifier);
        listConversionTransaction.forEach(c -> repository.save(c).block());

        var allByUserIdentifierFlux = repository.findAllByUserIdentifier(userIdentifier);

        StepVerifier
                .create(allByUserIdentifierFlux)
                .expectNextCount(0)
                .consumeNextWith(c -> assertTrue(listConversionTransaction.stream()
                        .anyMatch(conversionFromList -> conversionFromList.getIdentifier().equals(c.getIdentifier()))))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}