package com.lzw.flowablespringboot.service.impl;

import com.lzw.flowablespringboot.pojo.HifmResponse;
import com.lzw.flowablespringboot.service.FlowableTaskService;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LZW
 * @date 2020/5/20 14:47
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowableTaskServiceImpl implements FlowableTaskService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Override
    public Object getToBeProcessed(String userId) {
        // TODO 这里的Tenant不是写死的
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).taskTenantId("wxyx").orderByTaskCreateTime().desc().list();
        return Arrays.toString(taskList.toArray());
    }

    @Override
    public HifmResponse apply(String taskId) {
        HifmResponse hifmResponse = HifmResponse.success(200,"审批通过");
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            hifmResponse = HifmResponse.success(200,"流程不存在");
        }
        // 通过审核
        taskService.complete(taskId);
        return hifmResponse;
    }

    @Override
    public void genProcessDiagram(String processId) throws IOException {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        // 流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        // 使用流程实例ID,查询正在执行的执行对象表,返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        // 得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        // 获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(),
                                                          engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
