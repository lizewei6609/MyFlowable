package com.lzw.flowablespringboot.service.impl;

import com.lzw.flowablespringboot.service.FlowableHistoryProcessService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LZW
 * @date 2020/5/19 19:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowableHistoryProcessServiceImpl implements FlowableHistoryProcessService {

    @Autowired
    private HistoryService historyService;

    @Override
    public List<HistoricProcessInstance> getMyStartProcess(String userId) {
        return historyService.createHistoricProcessInstanceQuery()
                // .finished() 已完成的 .unfinished() 未完成的或者不加表示全部
                .startedBy(userId)
                // TODO 这里的Tenant不是写死的
                .processInstanceTenantId("wxyx")
                .orderByProcessInstanceStartTime().asc()
                .list();
    }
}
