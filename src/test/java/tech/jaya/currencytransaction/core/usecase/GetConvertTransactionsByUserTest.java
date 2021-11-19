package tech.jaya.currencytransaction.core.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GetConvertTransactionsByUserTest {

    @InjectMocks
    private GetConvertTransactionsByUser getConvertTransactionsByUser;

    @Mock
    private ConversionTransactionRepository conversionTransactionRepository;

    @Test
    void shouldGetAllCurrenciesByUserWithSuccess() {
        final var userIdentifier = "1";
        when(conversionTransactionRepository.findAllByUserIdentifier(userIdentifier))
                .thenReturn(FixtureConversionTransaction.fluxList(userIdentifier));

        List<ConversionTransaction> listReturned = new ArrayList<>(2);
        getConvertTransactionsByUser.execute(userIdentifier)
                .collectList()
                .subscribe(listReturned::addAll);

        assertEquals(2, listReturned.size());
    }

    @Test
    void shouldGetEmptyListCurrenciesByUserWithSuccess() {
        final var userIdentifier = "1";
        when(conversionTransactionRepository.findAllByUserIdentifier(userIdentifier))
                .thenReturn(Flux.empty());

        List<ConversionTransaction> listReturned = new ArrayList<>(2);
        getConvertTransactionsByUser.execute(userIdentifier)
                .collectList()
                .subscribe(listReturned::addAll);

        assertTrue(listReturned.isEmpty());
    }

}