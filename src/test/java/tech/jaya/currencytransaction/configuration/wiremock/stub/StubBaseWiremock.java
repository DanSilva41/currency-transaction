package tech.jaya.currencytransaction.configuration.wiremock.stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.MediaType;

public abstract class StubBaseWiremock {

    protected static final String GET_EXCHANGES_RATES_FILE = "mock/get-exchanges-rates.json";
    protected static final String ERROR_INVALID_CURRENCY_GET_EXCHANGES_RATES_FILE = "mock/error-invalid-currency-get-exchanges-rates.json";

    protected static void mock(final WireMockServer wireMockServer,
                               final String url,
                               final int status,
                               final String body) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(status)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(body)));
    }
}
