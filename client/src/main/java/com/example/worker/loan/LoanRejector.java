package com.example.worker.loan;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@ExternalTaskSubscription("requestRejecter")
public class LoanRejector implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        final Integer score = externalTask.getVariable("score");

        // complete the external task
        externalTaskService.complete(externalTask, Collections.singletonMap("rejectedAmount", score));

        log.info("The External Task {} has been completed! loan  has been rejected...", externalTask.getId());
    }
}
