package com.lzw.flowablespringboot.handler;

import lombok.Data;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LZW
 * @date 2020/5/27 13:39
 * @describe 资产接收者
 */
@Data
@Component("assetReceiverHandler")
public class AssetReceiverHandler implements TaskListener {

    private Expression defaultHandler;

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("资产接收者");
        System.out.println(defaultHandler.getValue(delegateTask));
    }
}
