package com.example.integrationtestexample.service;

import com.example.integrationtestexample.model.dto.CreateDocumentRq;
import com.example.integrationtestexample.model.dto.GetDocumentRs;
import com.example.integrationtestexample.model.entity.Document;
import com.example.integrationtestexample.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Transactional
    @Override
    public Long createDocument(CreateDocumentRq request) {
        Document document = new Document();
        document.setTitle(request.title());
        document.setContent(request.content());
        documentRepository.saveAndFlush(document);
        return document.getId();
    }

    @Override
    public GetDocumentRs getDocument(Long id) {
        Optional<Document> document = documentRepository.findById(id);
        Document entity = document.orElseThrow(() -> new RuntimeException("Документ не найден"));
        return new GetDocumentRs(entity.getId(), entity.getTitle(), entity.getContent());
    }
}
