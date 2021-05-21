package com.example.worker.payment.infrastructure;

import com.example.worker.payment.domain.UserCardActivityTester;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SecurityUserActivityScoreStore implements UserCardActivityTester {

    private final Random rnd = new Random();

    @SneakyThrows
    @Override
    public boolean isBigChargeAllowed(String userSid) {
        //execute business-logic
        Thread.sleep(1000);
        return rnd.nextBoolean();
    }
}
