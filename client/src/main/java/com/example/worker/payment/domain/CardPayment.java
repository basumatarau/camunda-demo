package com.example.worker.payment.domain;

import lombok.Value;

import java.util.Objects;
import java.util.UUID;


@Value
public class CardPayment {
    UUID internalTransactionId;
    String itemId;
    Long amount;
    String transactionToken;
    String securityId;

    private CardPayment(String itemId, Long amount, String transactionToken, String securityId) {
        this.internalTransactionId = UUID.randomUUID();
        this.itemId = itemId;
        this.amount = amount;
        this.transactionToken = transactionToken;
        this.securityId = securityId;
    }

    public static CardPayment of(String itemId, Long amount, String transactionToken, String securityId) {

        final String transactionTokenValue =
                Objects.requireNonNull(transactionToken, "transaction token must be provided.");
        final String merchItemId = Objects.requireNonNull(itemId, "merch identifier must be provided.");
        final Long amountValue = Objects.requireNonNull(amount, "charge amount must be provided");

        return new CardPayment(merchItemId, amountValue, transactionTokenValue, Objects.requireNonNull(securityId));
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "itemId='" + itemId + '\'' +
                ", amount='" + amount + '\'' +
                ", internalTransactionId='" + internalTransactionId.toString() + '\'' +
                '}';
    }
}
