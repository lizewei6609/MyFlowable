package com.lzw.flowablespringboot.pojo;

import lombok.Data;
import org.flowable.engine.impl.persistence.entity.ModelEntityImpl;
import org.flowable.engine.repository.Model;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HifmModel extends ModelEntityImpl {
    private static final long serialVersionUID = 1L;

    /**
     * 流程的xml
     */
    private String xml;

    /**
     * 文件
     */
    private MultipartFile file;
}
