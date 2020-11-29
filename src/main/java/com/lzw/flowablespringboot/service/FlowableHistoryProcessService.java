package com.lzw.flowablespringboot.service;

import org.flowable.engine.history.HistoricProcessInstance;

import java.util.List;

/**
 * @author LZW
 * @date 2020/5/19 19:37
 */
public interface FlowableHistoryProcessService {

    /**
     * 获取自己发起的流程实例
     * @param userId 用户id
     * @return List<HistoricProcessInstance>
     */
    List<HistoricProcessInstance> getMyStartProcess(String userId);
}
