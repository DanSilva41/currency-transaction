package tech.jaya.currencytransaction.support;

public enum ErrorMessage {

    RESOURCE_NOT_FOUND("global.resource-not-found"),
    REQUEST_BODY_MISSING("global.request-body-missing"),
    UNSUPPORTED_MEDIA_TYPE("global.unsupported-media-type");

    private final String identifier;

    ErrorMessage(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
