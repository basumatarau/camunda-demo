package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FoundProcurementDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Map<String, Object> variables = new HashMap<>();
        variables.put("amount", execution.getVariable("amount"));
        execution.getProcessEngineServices().getRuntimeService()
                 .startProcessInstanceByKey("fund_procurement_process", variables);

        System.out.println("###delegate invoked: additional funds requested (request sent)");
    }
}
