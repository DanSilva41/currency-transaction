package tech.jaya.currencytransaction.core.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseBusinessExceptionTest {

    @Test
    void shouldInstantiate() {
        BaseBusinessException baseBusinessException = new BaseBusinessException(ErrorMessage.FAIL_GET_EXCHANGE_RATES);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), baseBusinessException.getStatus());
        assertEquals(ErrorMessage.FAIL_GET_EXCHANGE_RATES, baseBusinessException.getErrorMessage());
        assertEquals(ErrorMessage.FAIL_GET_EXCHANGE_RATES.getIdentifier(), baseBusinessException.getMessage());
    }
}