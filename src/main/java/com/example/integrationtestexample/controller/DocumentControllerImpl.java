package com.example.integrationtestexample.controller;

import com.example.integrationtestexample.model.dto.CreateDocumentRq;
import com.example.integrationtestexample.model.dto.GetDocumentRs;
import com.example.integrationtestexample.service.DocumentService;
import com.example.integrationtestexample.service.ExternalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentControllerImpl implements DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ExternalDocumentService externalDocumentService;

    @Override
    public ResponseEntity<Long> createDocument(CreateDocumentRq request) {
        return ResponseEntity.ok(documentService.createDocument(request));
    }

    @Override
    public ResponseEntity<GetDocumentRs> getDocument(Long id) {
        return ResponseEntity.ok(documentService.getDocument(id));
    }

    @Override
    public ResponseEntity<GetDocumentRs> getExternalDocument(Long id) {
        return ResponseEntity.ok(externalDocumentService.getDocument(id));
    }
}
