package com.example.integrationtestexample.service;

import com.example.integrationtestexample.model.dto.GetDocumentRs;

public interface ExternalDocumentService {
    GetDocumentRs getDocument(Long id);
}
