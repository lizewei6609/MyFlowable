package com.lzw.flowablespringboot.controller;

import com.lzw.flowablespringboot.pojo.HifmResponse;
import com.lzw.flowablespringboot.service.FlowableTaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author LZW
 * @date 2020/5/20 9:52
 */
@RestController
@RequestMapping("/task")
public class FlowableTaskController {

    @Autowired
    private FlowableTaskService flowableTaskService;

    @GetMapping
    public Object getToBeProcessed(String userId) {
        return flowableTaskService.getToBeProcessed(userId);
    }

    @GetMapping("/{taskId}")
    public HifmResponse apply(@PathVariable String taskId) {
        return flowableTaskService.apply(taskId);
    }

    @GetMapping("/processDiagram/{processId}")
    public void genProcessDiagram(@PathVariable String processId) {
        try {
            flowableTaskService.genProcessDiagram(processId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
