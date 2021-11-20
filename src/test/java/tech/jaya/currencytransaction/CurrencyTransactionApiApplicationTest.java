package tech.jaya.currencytransaction;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyTransactionApiApplicationTest {

    @Autowired
    private WebTestClient webClient;

    @Value("${spring.application.name}")
    private String appName;

    @Test
    void shouldBeAbleToRun() {
        byte[] bytesResponseBody = webClient.get()
                .uri("/actuator/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult()
                .getResponseBody();

        assertNotNull(bytesResponseBody);
        final var response = new String(bytesResponseBody);
        assertTrue(response.contains("UP"));
    }

    @Test
    void shouldContainCorrectInformation() {
        byte[] bytesResponseBody = webClient.get()
                .uri("/actuator/info")
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult()
                .getResponseBody();

        assertNotNull(bytesResponseBody);
        final var response = new String(bytesResponseBody);
        assertTrue(response.contains("id"));
        assertTrue(response.contains(appName));
    }

}