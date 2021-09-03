package com.example.worker.funds;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@ExternalTaskSubscription("fundRequestProcessing")
public class FundProcurementManager implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        log.info("The External Task {} has been completed! funds have been requested for loan issue",
                 externalTask.getId());

        // complete the external task
        externalTaskService.complete(externalTask, Collections.singletonMap("success", Boolean.TRUE));
    }
}
