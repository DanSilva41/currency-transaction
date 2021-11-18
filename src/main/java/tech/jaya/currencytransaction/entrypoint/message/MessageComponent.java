package tech.jaya.currencytransaction.entrypoint.message;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;

@AllArgsConstructor
@Component
public class MessageComponent {

    private final MessageSource messageSource;

    public String getMessage(final ErrorMessage errorMessage) {
        return messageSource.getMessage(errorMessage.getIdentifier(), null, LocaleContextHolder.getLocale());
    }
}
