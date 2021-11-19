package tech.jaya.currencytransaction.configuration.wiremock.stub;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.http.HttpStatus;
import tech.jaya.currencytransaction.dataprovider.client.exchangerates.ExchangeRatesResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.util.StreamUtils.copyToString;

public class StubGetExchangeRates extends StubBaseWiremock {

    public static void setupOkStatus(WireMockServer wireMockServer, final String accessKey, final List<String> symbols) throws IOException {
        final String url = "/?access_key=" + accessKey + "&symbols=" + String.join("%2C", symbols);
        mock(wireMockServer, url, HttpStatus.OK.value(),
                copyToString(
                        ExchangeRatesResponse.class.getClassLoader().getResourceAsStream(GET_EXCHANGES_RATES_FILE),
                        StandardCharsets.UTF_8));
    }

    public static void setupBadRequestStatus(WireMockServer wireMockServer, final String accessKey, final List<String> symbols) throws IOException {
        final String url = "/?access_key=" + accessKey + "&symbols=" + String.join("%2C", symbols);
        mock(wireMockServer, url, HttpStatus.BAD_REQUEST.value(),
                copyToString(
                        ExchangeRatesResponse.class.getClassLoader().getResourceAsStream(ERROR_INVALID_CURRENCY_GET_EXCHANGES_RATES_FILE),
                        StandardCharsets.UTF_8));
    }

}
