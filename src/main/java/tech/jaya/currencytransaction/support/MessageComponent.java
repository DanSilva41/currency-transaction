package tech.jaya.currencytransaction.support;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

    private final MessageSource messageSource;

    public MessageComponent(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(final ErrorMessage errorMessage) {
        return messageSource.getMessage(errorMessage.getIdentifier(), null, LocaleContextHolder.getLocale());
    }
}
