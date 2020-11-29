package com.lzw.flowablespringboot.controller;

import com.lzw.flowablespringboot.pojo.HifmProcess;
import com.lzw.flowablespringboot.pojo.HifmResponse;
import com.lzw.flowablespringboot.service.FlowableProcessInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther LZW
 * @date 2020/5/12 11:11
 */
@RestController
@RequestMapping("/process")
public class FlowableProcessInstanceController {

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    @PostMapping
    public HifmResponse add(@RequestBody HifmProcess hifmProcess) {
        HifmResponse hifmResponse;
        try {
            hifmResponse = flowableProcessInstanceService.add(hifmProcess);
        } catch (Exception e){
            hifmResponse = HifmResponse.success(101, e.getMessage());
        }
        return hifmResponse;
    }

}
