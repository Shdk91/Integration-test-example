package com.example.integrationtestexample.service;

import com.example.integrationtestexample.model.dto.CreateDocumentRq;
import com.example.integrationtestexample.model.dto.GetDocumentRs;


public interface DocumentService {

    Long createDocument(CreateDocumentRq request);


    GetDocumentRs getDocument(Long id);
}
