package com.lzw.flowablespringboot.handler;

import lombok.Data;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.api.Task;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author LZW
 * @date 20200/5/8 14:45
 */
@Data
@Component("managerTaskHandler")
public class ManagerTaskHandler implements TaskListener {

    private Expression clientName;

    private Expression companyName;

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("经理");
        /*System.out.println(clientName.getValue(delegateTask));
        System.out.println(companyName.getValue(delegateTask));*/
    }

}
