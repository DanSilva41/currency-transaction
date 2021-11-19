package tech.jaya.currencytransaction.core.exception;

public enum ErrorMessage {

    RESOURCE_NOT_FOUND("global.resource-not-found"),
    REQUEST_BODY_MISSING("global.request-body-missing"),
    UNSUPPORTED_MEDIA_TYPE("global.unsupported-media-type"),
    CURRENCIES_MUST_BE_DIFFERENT("validation.must-be-different-currencies"),
    WAS_NOT_POSSIBLE_GET_EXCHANGE_RATES("error.was-not-possible-get-exchange-rates"),
    CONVERSION_ALREADY_TAKEN_PLACE("error.conversion-has-already-taken"),
    FAIL_GET_EXCHANGE_RATES("error.fail-get-exchange-rates");

    private final String identifier;

    ErrorMessage(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
