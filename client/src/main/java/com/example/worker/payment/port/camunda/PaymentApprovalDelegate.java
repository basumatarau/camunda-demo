package com.example.worker.payment.port.camunda;

import com.example.worker.payment.domain.CardActivity;
import com.example.worker.payment.domain.CardChargeProver;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@ExternalTaskSubscription("payment-approval")
public class PaymentApprovalDelegate implements ExternalTaskHandler {

    private final CardChargeProver chargeProver;

    public PaymentApprovalDelegate(CardChargeProver chargeProver) {
        this.chargeProver = chargeProver;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        final String sid = externalTask.getVariable("sid");

        final boolean isApproved = chargeProver.approveActivity(CardActivity.of(sid));
        log.info("approval for transaction for user with sid '{}' resolved {}", sid, isApproved ? "positively" : "negatively");

        externalTaskService.complete(externalTask, Collections.singletonMap("approved", Variables.booleanValue(isApproved)));
    }
}
