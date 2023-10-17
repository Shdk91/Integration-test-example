package com.example.integrationtestexample;

import com.example.integrationtestexample.model.dto.CreateDocumentRq;
import com.example.integrationtestexample.model.dto.GetDocumentRs;
import com.example.integrationtestexample.model.entity.Document;
import com.example.integrationtestexample.repository.DocumentRepository;
import com.example.integrationtestexample.service.ExternalDocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DocumentControllerTest extends BaseTest {

    @Autowired
    DocumentRepository documentRepository;

    @MockBean
    ExternalDocumentService mockExternalDocumentService;

    @Test
    public void createDocumentSuccessTest() throws Exception {
        CreateDocumentRq rq = new CreateDocumentRq("test", "test");
         MvcResult result = mockMvc.perform(post("/document")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), 200);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void getDocumentTest(boolean isSuccess) throws Exception {
        Long id = 5L;
        if (isSuccess) {
            var document = new Document();
            document.setContent("test");
            document.setTitle("test");
            documentRepository.saveAndFlush(document);
            id = document.getId();
        }
        MvcResult result = mockMvc.perform(get("/document/" + id))
                .andDo(print())
                .andReturn();

        if (isSuccess) {
            GetDocumentRs getDocumentRs = objectMapper
                    .readValue(result.getResponse().getContentAsString(), GetDocumentRs.class);
            assertEquals("test", getDocumentRs.title());
            assertEquals("test", getDocumentRs.content());
        } else {
            assertEquals(500, result.getResponse().getStatus());
        }
    }

    @Test
    public void getExternalDocumentTest() throws Exception {
        GetDocumentRs mockDocument = new GetDocumentRs(2L, "mock", "mock");
        when(mockExternalDocumentService.getDocument(any())).thenReturn(mockDocument);

        MvcResult result = mockMvc.perform(get("/document/external/" + 5))
                .andDo(print())
                .andReturn();

        GetDocumentRs getDocumentRs = objectMapper
                .readValue(result.getResponse().getContentAsString(), GetDocumentRs.class);

        assertEquals("mock", getDocumentRs.title());
        assertEquals("mock", getDocumentRs.content());

//        verify(mockExternalDocumentService).getDocument(any());
//        assertEquals("External", getDocumentRs.title());
//        assertEquals("External", getDocumentRs.content());
    }
}
