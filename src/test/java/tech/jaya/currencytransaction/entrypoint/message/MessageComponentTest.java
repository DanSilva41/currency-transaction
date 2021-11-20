package tech.jaya.currencytransaction.entrypoint.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageComponentTest {

    @Autowired
    private MessageComponent messageComponent;

    @Test
    void shouldGetMessageWithoutSuccess() {
        final var message = messageComponent.getMessage(ErrorMessage.RESOURCE_NOT_FOUND);
        assertNotNull(message);
        assertEquals("Resource not found", message);
    }
}