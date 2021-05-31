package com.example.worker.loan;

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
@ExternalTaskSubscription("creditScoreChecker")
public class CreditScoreGenerator implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        int defaultScore = externalTask.getVariable("defaultScore");

        List<Integer> creditScores = new ArrayList<>(Arrays.asList(defaultScore, 9, 1, 4, 10));

        // create an object typed variable
        ObjectValue creditScoresObject = Variables.objectValue(creditScores).create();

        // complete the external task
        externalTaskService.complete(externalTask, Collections.singletonMap("creditScores", creditScoresObject));

        log.info("The External Task {} has been completed!", externalTask.getId());
    }
}
