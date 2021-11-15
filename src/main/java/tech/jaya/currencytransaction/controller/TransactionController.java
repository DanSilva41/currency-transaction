package tech.jaya.currencytransaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tech.jaya.currencytransaction.controller.vm.ConversionTransactionResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Reactive REST controller to manage currencies transactions
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping("/user/{userId}")
    public Flux<ConversionTransactionResponse> allCurrenciesTransactions(@PathVariable("userId") final String userId) {
        log.info("allCurrenciesTransactions: userId={}", userId);
        return Flux
                .fromIterable(list());
    }

    private List<ConversionTransactionResponse> list() {
        return Arrays.asList(
                new ConversionTransactionResponse(),
                new ConversionTransactionResponse(),
                new ConversionTransactionResponse()
        );
    }

}
