package com.example.integrationtestexample;

import com.example.integrationtestexample.model.entity.Document;
import com.example.integrationtestexample.repository.DocumentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IntegrationTestExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IntegrationTestExampleApplication.class, args);
        DocumentRepository bean = context.getBean(DocumentRepository.class);
        Document document = new Document();
        document.setTitle("title");
        document.setContent("content");
        bean.saveAndFlush(document);
    }

}
