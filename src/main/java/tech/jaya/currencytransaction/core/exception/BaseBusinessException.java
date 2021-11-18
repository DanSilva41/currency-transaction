package tech.jaya.currencytransaction.core.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class BaseBusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7941783366979510547L;

    private final ErrorMessage errorMessage;
    private final int status;

    public BaseBusinessException(final ErrorMessage errorMessage) {
        super(errorMessage.getIdentifier());
        this.errorMessage = errorMessage;
        this.status = HttpStatus.UNPROCESSABLE_ENTITY.value();
    }

    public int getStatus() {
        return status;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
