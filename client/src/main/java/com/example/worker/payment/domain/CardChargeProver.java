package com.example.worker.payment.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardChargeProver {

    private final UserCardActivityTester securityActionProver;

    public CardChargeProver(UserCardActivityTester securityActionProver) {
        this.securityActionProver = securityActionProver;
    }

    public boolean approveActivity(CardActivity activity) {
        return securityActionProver.isBigChargeAllowed(activity.getSid());
    }
}
