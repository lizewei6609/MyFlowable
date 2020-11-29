package com.lzw.flowablespringboot.handler;


import lombok.Data;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * @author LZW
 * @date 20200/5/8 14:45
 */
@Data
@Component("bossTaskHandler")
public class BossTaskHandler implements TaskListener {

    private Expression clientName;

    private Expression companyName;


    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("老板");
        /*System.out.println(clientName.getValue(delegateTask));
        System.out.println(companyName.getValue(delegateTask));*/
    }

}
