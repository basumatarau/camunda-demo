package com.example.worker.loan;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Data
public class CreditRequestInstance implements Serializable {

    public static final Random rnd = new Random();

    private UUID id;
    private Integer amount;
    private Integer score;

    public static CreditRequestInstance of(Integer score) {
        final CreditRequestInstance creditRequestInstance = new CreditRequestInstance();
        creditRequestInstance.setAmount(rnd.nextInt(10000));
        creditRequestInstance.setScore(Objects.requireNonNull(score));
        creditRequestInstance.setId(UUID.randomUUID());
        return creditRequestInstance;
    }
}
