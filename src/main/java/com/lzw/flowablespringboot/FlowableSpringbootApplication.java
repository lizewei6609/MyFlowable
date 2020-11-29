package com.lzw.flowablespringboot;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * FlowableSpringbootApplication
 *
 * @author puhaiyang
 * @date 2018/12/19
 */
@EnableTransactionManagement
@SpringBootApplication
public class FlowableSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableSpringbootApplication.class, args);
    }

}

