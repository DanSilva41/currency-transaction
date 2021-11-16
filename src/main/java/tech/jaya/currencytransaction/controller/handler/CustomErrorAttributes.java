package tech.jaya.currencytransaction.controller.handler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import tech.jaya.currencytransaction.support.ErrorMessage;
import tech.jaya.currencytransaction.support.MessageComponent;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private final MessageComponent messageComponent;

    public CustomErrorAttributes(MessageComponent messageComponent) {
        this.messageComponent = messageComponent;
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        var defaultErrorAttributes = super.getErrorAttributes(request, options);
        var throwable = getError(request);
        return Map.of("body", buildCustomErrorAttributes(throwable, defaultErrorAttributes));
    }

    private ApiError buildCustomErrorAttributes(Throwable throwable, Map<String, Object> defaultErrorAttributes) {
        var apiError = buildDefaultApiErrorAttributes(defaultErrorAttributes);
        if (apiError.getStatus() == HttpStatus.NOT_FOUND.value()) {
            apiError.setMessage(messageComponent.getMessage(ErrorMessage.RESOURCE_NOT_FOUND));
        } else if (throwable instanceof ResponseStatusException) {
            var responseStatusException = (ResponseStatusException) throwable;

            if (responseStatusException instanceof WebExchangeBindException) {
                var webExchangeBindException = (WebExchangeBindException) throwable;
                this.handleWebExchangeBindException(webExchangeBindException, apiError);
            } else if (responseStatusException instanceof ServerWebInputException) {
                apiError.setMessage(messageComponent.getMessage(ErrorMessage.REQUEST_BODY_MISSING));
            } else if (responseStatusException instanceof UnsupportedMediaTypeStatusException) {
                apiError.setMessage(messageComponent.getMessage(ErrorMessage.UNSUPPORTED_MEDIA_TYPE));
            }
        }
        return apiError;

    }

    private ApiError buildDefaultApiErrorAttributes(Map<String, Object> defaultErrorAttributes) {
        final var requestId = Optional.ofNullable(defaultErrorAttributes.get("requestId"))
                .map(String::valueOf)
                .orElse(null);
        final var status = (int) defaultErrorAttributes.get("status");
        return new ApiError(requestId, status);
    }


    private void handleWebExchangeBindException(WebExchangeBindException webExchangeBindException,
                                                ApiError apiError) {

        var fieldErrors = webExchangeBindException.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            apiError.setErrors(fieldErrors.stream()
                    .map(error -> new ApiFieldError(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList()));
        }
    }

}
