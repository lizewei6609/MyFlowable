package com.lzw.flowablespringboot.service;

import com.lzw.flowablespringboot.pojo.HifmProcess;
import com.lzw.flowablespringboot.pojo.HifmResponse;

/**
 * @author LZW
 * @date 2020/5/18 16:02
 */
public interface FlowableProcessInstanceService {

    /**
     * 新增实例化流程
     * @param hifmProcess
     * @return
     */
    HifmResponse add(HifmProcess hifmProcess);
}
