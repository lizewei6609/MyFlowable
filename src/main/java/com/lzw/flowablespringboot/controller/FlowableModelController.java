package com.lzw.flowablespringboot.controller;

import com.lzw.flowablespringboot.pojo.HifmModel;
import com.lzw.flowablespringboot.pojo.HifmResponse;
import com.lzw.flowablespringboot.service.FlowableModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther LZW
 * @date 2020/5/12 11:11
 */
@RestController
@RequestMapping("/model")
public class FlowableModelController {

    @Autowired
    private final FlowableModelService flowableModelService;
    public FlowableModelController(FlowableModelService flowableModelService) {
        this.flowableModelService = flowableModelService;
    }

    @PostMapping("/add")
    public HifmResponse add(@RequestBody HifmModel hifmModel) {
        HifmResponse hifmResponse;
        try {
            hifmResponse = flowableModelService.add(hifmModel);
        } catch (Exception e){
            hifmResponse = HifmResponse.success(101, e.getMessage());
        }
        return hifmResponse;
    }

    @PutMapping
    public HifmResponse update(@RequestBody HifmModel hifmModel) {
        HifmResponse hifmResponse;
        try {
            hifmResponse = flowableModelService.update(hifmModel);
        } catch (Exception e){
            hifmResponse = HifmResponse.success(101, e.getMessage());
        }
        return hifmResponse;
    }

    @PostMapping("/importModel")
    public HifmResponse importModel(HifmModel hifmModel) {
        HifmResponse hifmResponse;
        try {
            hifmResponse = flowableModelService.importModel(hifmModel);
        } catch (Exception e){
            hifmResponse = HifmResponse.success(101, e.getMessage());
        }
        return hifmResponse;
    }
}
