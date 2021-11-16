package tech.jaya.currencytransaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.controller.vm.ConversionTransactionRequest;
import tech.jaya.currencytransaction.controller.vm.ConversionTransactionResponse;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Reactive REST controller to manage currencies transactions
 */
@Validated
@RestController
@RequestMapping("/transaction")
public class ConversionTransactionController {

    private static final Logger log = LoggerFactory.getLogger(ConversionTransactionController.class);

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ConversionTransactionResponse> allCurrenciesTransactions(@PathVariable("userId") final String userId) {
        log.info("allCurrenciesTransactions: userId = {}", userId);
        return Flux
                .fromIterable(list());
    }

    @PostMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ConversionTransactionResponse> convertCurrencies(@PathVariable("userId") final String userId,
                                                                 @Valid @RequestBody final ConversionTransactionRequest request) {
        log.info("convertCurrencies: userId = {} | request = {}", userId, request);
        return Mono
                .just(list().get(0));
    }

    private List<ConversionTransactionResponse> list() {
        return Arrays.asList(
                new ConversionTransactionResponse(),
                new ConversionTransactionResponse(),
                new ConversionTransactionResponse()
        );
    }

}
