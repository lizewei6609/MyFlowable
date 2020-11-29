package com.lzw.flowablespringboot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageSize;

    private int pageNum;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序规则， ase升序，desc降序
     */
    private String order;
}
