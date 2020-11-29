package com.lzw.flowablespringboot.service;

import com.lzw.flowablespringboot.pojo.HifmResponse;

import java.io.IOException;

/**
 * @author LZW
 * @date 2020/5/20 14:47
 */
public interface FlowableTaskService {

    /**
     * 获取待审批的列表
     * @param userId 用户id
     * @return Object
     */
    Object getToBeProcessed(String userId);

    /**
     * 审批通过
     * @param taskId 任务id
     * @return HifmResponse
     */
    HifmResponse apply(String taskId);

    /**
     * 生成流程图
     * @param processId
     */
    void genProcessDiagram(String processId) throws IOException;
}
