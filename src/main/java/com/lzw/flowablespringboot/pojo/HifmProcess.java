package com.lzw.flowablespringboot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LZW
 * @date 2020/5/18 20:31
 */
@Data
public class HifmProcess implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 租户
     */
    private String tenant;

    /**
     * 流程定义键
     */
    private String processKey;

    /**
     * 任务用户
     */
    private String startUser;

    /**
     * 条件变量
     */
    private Map<String,String> parsMap;

    /**
     * 描述
     */
    private String description;
}
