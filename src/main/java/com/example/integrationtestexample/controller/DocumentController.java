package com.example.integrationtestexample.controller;

import com.example.integrationtestexample.model.dto.CreateDocumentRq;
import com.example.integrationtestexample.model.dto.GetDocumentRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/document")
public interface DocumentController {

    @PostMapping
    ResponseEntity<Long> createDocument(@RequestBody CreateDocumentRq request);

    @GetMapping("/{id}")
    ResponseEntity<GetDocumentRs> getDocument(@PathVariable Long id);

    @GetMapping("/external/{id}")
    ResponseEntity<GetDocumentRs> getExternalDocument(@PathVariable Long id);
}
