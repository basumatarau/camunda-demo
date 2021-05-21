package com.example.worker.payment.domain;

import lombok.Value;

import java.util.Objects;

@Value(staticConstructor = "of")
public class CardActivity {
    String sid;

    private CardActivity(String sid) {
        this.sid = Objects.requireNonNull(sid, "user security id must be provided");
    }
}
