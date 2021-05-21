package com.example.worker.payment.port.camunda;

import com.example.worker.payment.domain.CardCharger;
import com.example.worker.payment.domain.CardPayment;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ExternalTaskSubscription("charge-card")
public class ChargeCardWorker implements ExternalTaskHandler {

    private final CardCharger cardCharger;

    public ChargeCardWorker(CardCharger cardCharger) {
        this.cardCharger = cardCharger;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        final String securityId = externalTask.getVariable("sid");
        final String transactionToken = externalTask.getVariable("token");
        final String item = externalTask.getVariable("item");
        final Long amount = externalTask.getVariable("amount");

        cardCharger.chargeCard(CardPayment.of(item, amount, transactionToken, securityId));

        externalTaskService.complete(externalTask);
    }
}
