package tech.jaya.currencytransaction.dataprovider.exchangerates;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.test.StepVerifier;
import tech.jaya.currencytransaction.configuration.wiremock.WiremockConfiguration;
import tech.jaya.currencytransaction.configuration.wiremock.stub.StubGetExchangeRates;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.dataprovider.client.exchangerates.ExchangeRatesProvider;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@EnableConfigurationProperties
@ContextConfiguration(classes = WiremockConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeRatesProviderTest {

    @Autowired
    private ExchangeRatesProvider exchangeRatesProvider;

    @Autowired
    private WireMockServer wireMockServer;

    @Value("${app.client.exchangeratesapi.accessKey}")
    private String exchangeRatesAccessKey;

    @Test
    void shouldReturnConversionRatesWithSuccess() throws IOException {
        final String originCurrency = "BRL";
        final String destinationCurrency = "JPY";
        wireMockServer.resetRequests();
        StubGetExchangeRates.setupOkStatus(wireMockServer, exchangeRatesAccessKey, List.of(originCurrency, destinationCurrency));

        exchangeRatesProvider.getConversionRates(originCurrency, destinationCurrency)
                .doOnSuccess(exchangesRates -> {
                    assertTrue(exchangesRates.isSuccess());
                    assertFalse(exchangesRates.getRates().isEmpty());
                })
                .doOnError((e) -> fail("Oops, unexpected behavior..."))
                .block();
    }

    @Test
    void shouldThrowFailureWhenInvalidCurrencyConversionRates() throws IOException {
        final String originCurrency = "ZZZ";
        final String destinationCurrency = "AAA";
        wireMockServer.resetRequests();
        StubGetExchangeRates.setupBadRequestStatus(wireMockServer, exchangeRatesAccessKey, List.of(originCurrency, destinationCurrency));

        StepVerifier.create(exchangeRatesProvider.getConversionRates(originCurrency, destinationCurrency))
                .expectError(BaseBusinessException.class)
                .verify();
    }
}