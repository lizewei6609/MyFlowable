package com.lzw.flowablespringboot.service;

import com.lzw.flowablespringboot.pojo.HifmModel;
import com.lzw.flowablespringboot.pojo.HifmResponse;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * @auther LZW
 * @date 2020/5/12 19:58
 */
public interface FlowableModelService {
    /**
     * 新增和部署流程
     * @param hifmModel 流程对象
     * @return HifmResponse
     * @throws IOException io异常
     * @throws XMLStreamException 解析xml异常
     */
    HifmResponse add(HifmModel hifmModel) throws IOException, XMLStreamException;

    /**
     * 修改流程
     * @param hifmModel 流程对象
     * @return HifmResponse
     * @throws IOException io异常
     * @throws XMLStreamException 解析xml异常
     */
    HifmResponse update(HifmModel hifmModel) throws IOException, XMLStreamException;

    /**
     * 导入流程
     * @param hifmModel 流程对象
     * @return HifmResponse
     */
    HifmResponse importModel(HifmModel hifmModel) throws IOException, XMLStreamException;
}
