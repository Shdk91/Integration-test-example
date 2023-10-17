package com.example.integrationtestexample.service;

import com.example.integrationtestexample.model.dto.GetDocumentRs;
import org.springframework.stereotype.Service;

@Service
public class ExternalDocumentServiceImpl implements ExternalDocumentService {
    @Override
    public GetDocumentRs getDocument(Long id) {
        GetDocumentRs doc = new GetDocumentRs(2L, "External", "External");
        return doc;
    }
}
