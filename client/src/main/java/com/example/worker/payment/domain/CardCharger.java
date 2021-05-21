package com.example.worker.payment.domain;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardCharger {

    @SneakyThrows
    public void chargeCard(CardPayment payment) {
        log.info("Charging credit card with an amount of '{}'€ for the item '{}}'...", payment.getAmount(), payment.getItemId());
        Thread.sleep(1000);
        log.info("Payment transaction with id='{}' has been committed successfully.", payment.getInternalTransactionId());
    }
}
