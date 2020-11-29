package com.lzw.flowablespringboot.handler;

import lombok.Data;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LZW
 * @date 2020/5/27 13:25
 * @describe 仓库管理员
 */
@Data
@Component("storekeeperHandler")
public class StorekeeperHandler implements TaskListener {

    private Expression defaultHandler;

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("仓库管理员");
        System.out.println(defaultHandler.getValue(delegateTask));
    }
}
