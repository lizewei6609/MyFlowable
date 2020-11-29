package com.lzw.flowablespringboot.service.impl;

import com.lzw.flowablespringboot.pojo.HifmProcess;
import com.lzw.flowablespringboot.pojo.HifmResponse;
import com.lzw.flowablespringboot.service.FlowableProcessInstanceService;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZW
 * @date 2020/5/18 16:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowableProcessInstanceServiceImpl implements FlowableProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public HifmResponse add(HifmProcess hifmProcess) {
        Map<String,Object> variables = new HashMap<>();
        // tenant租户标识
        String tenant = hifmProcess.getTenant();
        // 获取prcoessKey
        String processKey = hifmProcess.getProcessKey();
        // 启动前必须开启,这样才能取得流程发起人
        String  userId = hifmProcess.getStartUser();
        // 设置流程实例的发起人是当前用户
        Authentication.setAuthenticatedUserId(userId);
        // 判断processKey为空直接返回null
        if (StringUtils.isEmpty(processKey)){
            return null;
        }
        // variables.put("taskUser","123");
        variables.put("description",hifmProcess.getDescription());
        // TODO 这里的Tenant不是写死的
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKeyAndTenantId(processKey, variables, tenant);
        return HifmResponse.success(200,"提交成功,流程Id为：" + processInstance.getId());
    }
}
