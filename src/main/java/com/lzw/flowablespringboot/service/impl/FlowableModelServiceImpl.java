package com.lzw.flowablespringboot.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lzw.flowablespringboot.mapper.MyRepositoryMapper;
import com.lzw.flowablespringboot.pojo.HifmModel;
import com.lzw.flowablespringboot.pojo.HifmResponse;
import com.lzw.flowablespringboot.service.FlowableModelService;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.persistence.entity.ModelEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * @auther LZW
 * @date 2020/5/12 20:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowableModelServiceImpl implements FlowableModelService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private MyRepositoryMapper myRepositoryMapper;

    protected BpmnXMLConverter bpmnXmlConverter = new BpmnXMLConverter();
    protected BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();

    @Override
    public HifmResponse add(HifmModel hifmModel) throws IOException, XMLStreamException {
        HifmResponse hifmResponse = HifmResponse.success(200,"创建模板成功");
        // 解析上传的文件转换为流
        InputStream inputStream;
        if (null != hifmModel.getXml()) {
            inputStream = new ByteArrayInputStream(hifmModel.getXml().getBytes());
        } else {
            inputStream = hifmModel.getFile().getInputStream();
        }
        // 创建XML工厂
        // XMLInputFactory xmlInputFactory = XmlUtil.newXMLInputFactory(true);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        // 把流转换为XMLStreamReader
        XMLStreamReader xtr = xmlInputFactory.createXMLStreamReader(inputStream);
        // 把XMLStreamReader转换为jsonNode
        BpmnModel bpmnModel = bpmnXmlConverter.convertToBpmnModel(xtr);
        ObjectNode modelNode = bpmnJsonConverter.convertToJson(bpmnModel);
        // 校验流程的工厂
        ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();
        // 校验bpmn文件是否有问题
        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
        List<ValidationError> validateList = defaultProcessValidator.validate(bpmnModel);
        if (0 < validateList.size()) {
            StringBuffer es = new StringBuffer();
            validateList.forEach(ve -> es.append(ve.toString()).append("/n"));
            return HifmResponse.success(200,"模板验证失败，原因: " + es.toString());
        }
        // 获取流程对象
        org.flowable.bpmn.model.Process process = bpmnModel.getMainProcess();
        String fileName = process.getName();
        if (CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
            return HifmResponse.success(200,"定义中未找到任何过程" + fileName);
        }
        String description = process.getDocumentation();
        String name = process.getId();
        if (StringUtils.isNotEmpty(process.getName())) {
            name = process.getName();
        }
        Model model = new ModelEntityImpl();
        List<Map<String,String>> models = myRepositoryMapper.findByKeyAndTypeAndTenant(process.getId(), hifmModel.getCategory(), hifmModel.getTenantId());
        if (CollectionUtils.isNotEmpty(models)) {
            Map<String,String> updateModel = models.get(0);
            repositoryService.deleteModel(updateModel.get("ID_"));
        }
        model.setName(name);
        model.setKey(process.getId());
        model.setMetaInfo(modelNode.toString());
        model.setCategory(hifmModel.getCategory());
        // TODO 这里的Tenant不是写死的
        model.setTenantId(hifmModel.getTenantId());
        // 保存流程模型
        repositoryService.saveModel(model);
        // 部署流程
        Deployment deploy = repositoryService.createDeployment()
                              .name(model.getName())
                              .key(model.getKey())
                              .category(hifmModel.getCategory())
                              .tenantId(hifmModel.getTenantId())
                              .addBpmnModel(model.getKey() + ".bpmn", bpmnModel)
                              .deploy();
        return hifmResponse;
    }


    private DataSource createDataSource(String url,String dbuser,String password) {
        return DataSourceBuilder.create()
                .url(url)
                .username(dbuser)
                .password(password)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Override
    public HifmResponse update(HifmModel hifmModel) throws IOException, XMLStreamException {
        HifmResponse hifmResponse = HifmResponse.success(200,"修改模板成功");
        add(hifmModel);
        return hifmResponse;
    }

    @Override
    public HifmResponse importModel(HifmModel hifmModel) throws IOException, XMLStreamException {
        HifmResponse hifmResponse = HifmResponse.success(200,"导入模板成功");
        add(hifmModel);
        return hifmResponse;
    }
}
