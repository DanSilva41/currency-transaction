package tech.jaya.currencytransaction.entrypoint.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.usecase.ConvertCurrencies;
import tech.jaya.currencytransaction.core.usecase.GetConvertTransactionsByUser;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransactionRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConversionTransactionControllerTest {

    @MockBean
    private ConvertCurrencies convertCurrencies;

    @MockBean
    private GetConvertTransactionsByUser getConvertTransactionsByUser;

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldReturnStatusOKWhenThereAreCurrenciesTransactions() {
        final var userIdentifier = "1";
        final var conversionsTransaction = FixtureConversionTransaction.fluxList(userIdentifier);
        when(getConvertTransactionsByUser.execute(userIdentifier)).thenReturn(conversionsTransaction);

        webClient.get()
                .uri("/transaction/user/".concat(userIdentifier))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].identifier").exists()
                .jsonPath("$[0].userIdentifier").exists()
                .jsonPath("$[0].originCurrency").exists()
                .jsonPath("$[0].destinationCurrency").exists()
                .jsonPath("$[0].destinationValue").exists()
                .jsonPath("$[0].conversionRate").exists()
                .jsonPath("$[0].transactionTime").exists();

        Mockito.verify(getConvertTransactionsByUser, times(1)).execute(userIdentifier);
    }

    @Test
    void shouldReturnNotFoundWhenThereAreNoCurrenciesTransactionsForUser() {
        final var userIdentifier = "1";
        when(getConvertTransactionsByUser.execute(userIdentifier)).thenReturn(Flux.empty());

        webClient.get()
                .uri("/transaction/user/".concat(userIdentifier))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();

        Mockito.verify(getConvertTransactionsByUser, times(1)).execute(userIdentifier);
    }

    @Test
    void shouldReturnStatusCreatedWhenConvertCurrenciesWithSuccess() {
        final var userIdentifier = "1";
        final var conversionTransaction = FixtureConversionTransaction.one(userIdentifier);
        when(convertCurrencies.execute(any(ConversionTransaction.class))).thenReturn(Mono.just(conversionTransaction));

        webClient.post()
                .uri("/transaction/user/".concat(userIdentifier))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(FixtureConversionTransactionRequest.withDefaultValues()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.identifier").isEqualTo(conversionTransaction.getIdentifier())
                .jsonPath("$.userIdentifier").isEqualTo(conversionTransaction.getUserIdentifier())
                .jsonPath("$.originCurrency").isEqualTo(conversionTransaction.getOriginCurrency())
                .jsonPath("$.destinationCurrency").isEqualTo(conversionTransaction.getDestinationCurrency())
                .jsonPath("$.destinationValue").isEqualTo(conversionTransaction.getDestinationValue())
                .jsonPath("$.conversionRate").isEqualTo(conversionTransaction.getConversionRate())
                .jsonPath("$.transactionTime").isNotEmpty();

        Mockito.verify(convertCurrencies, times(1)).execute(any(ConversionTransaction.class));
    }

    @Test
    void shouldReturnStatusBadRequestWhenRequestNotHaveMandatoryFields() {
        final var userIdentifier = "1";

        webClient.post()
                .uri("/transaction/user/".concat(userIdentifier))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(FixtureConversionTransactionRequest.withoutMandatoryFields()))
                .exchange()
                .expectStatus().isBadRequest();

        Mockito.verify(convertCurrencies, times(0)).execute(any(ConversionTransaction.class));
    }

    @Test
    void shouldReturnStatusBadRequestWhenRequestHaveInvalidFields() {
        final var userIdentifier = "1";

        webClient.post()
                .uri("/transaction/user/".concat(userIdentifier))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(FixtureConversionTransactionRequest.withInvalidFields()))
                .exchange()
                .expectStatus().isBadRequest();

        Mockito.verify(convertCurrencies, times(0)).execute(any(ConversionTransaction.class));
    }

}