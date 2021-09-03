package com.example.worker.loan;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@ExternalTaskSubscription("creditScoreChecker")
public class CreditScoreManager implements ExternalTaskHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        int defaultScore = externalTask.getVariable("defaultScore");

/*        List<String> creditScores = Stream.of(defaultScore, 9, 1, 4, 10)
                                                         .map(CreditRequestInstance::of)
                                                         .map(cri -> {
                                                             try {
                                                                 return objectMapper.writeValueAsString(cri);
                                                             } catch (Exception e){
                                                                 return "{\"value\":\"null\"}";
                                                             }
                                                         })
                                                         .collect(Collectors.toList());*/

        /*List<CreditRequestInstance> creditScores = Stream.of(defaultScore, 9, 1, 4, 10)
                                          .map(CreditRequestInstance::of)
                                          .collect(Collectors.toList());*/

        List<Integer> creditScores = new ArrayList<>(Arrays.asList(defaultScore, 9, 1, 4, 10));

        // create an object typed variable
        /*ObjectValue creditScoresObject = Variables.objectValue(creditScores)
                                                  .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                                                  .create();*/

        ObjectValue creditScoresObject = Variables.objectValue(creditScores).create();

        // complete the external task
        externalTaskService.complete(externalTask, Collections.singletonMap("creditScores", creditScoresObject));

        log.info("The External Task {} has been completed! Credit request has been split and sent to processing",
                 externalTask.getId());
    }
}
