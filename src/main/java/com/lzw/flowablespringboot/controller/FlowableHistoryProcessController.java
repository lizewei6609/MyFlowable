package com.lzw.flowablespringboot.controller;

import com.lzw.flowablespringboot.service.FlowableHistoryProcessService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LZW
 * @date 2020/5/19 19:35
 */
@RestController
@RequestMapping("/history")
public class FlowableHistoryProcessController {

    @Autowired
    private FlowableHistoryProcessService flowableHistoryProcessService;

    @GetMapping("/{userId}")
    public List<HistoricProcessInstance> getMyStartProcess(@PathVariable String userId) {
        return flowableHistoryProcessService.getMyStartProcess(userId);
    }
}
