package tech.jaya.currencytransaction.entrypoint.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private final String requestId;
    @JsonIgnore
    private int status;
    private String message;
    private List<ApiFieldError> errors;

    public ApiError(String requestId, int status) {
        this.requestId = requestId;
        this.status = status;
    }

    public ApiError(String requestId, int status, List<ApiFieldError> errors) {
        this.requestId = requestId;
        this.status = status;
        this.errors = errors;
    }

    public String getRequestId() {
        return requestId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiFieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiFieldError> errors) {
        this.errors = errors;
    }
}
