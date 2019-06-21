package org.sut.cashmachine.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.ReceiptTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.converter.ReceiptConverter;
import org.sut.cashmachine.rest.dto.AuthResponse;
import org.sut.cashmachine.rest.dto.LoginRequest;
import org.sut.cashmachine.rest.dto.ReceiptDto;
import org.sut.cashmachine.rest.dto.ReceiptPageableResponse;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(ClientRegistrationConfig.class)
@TestPropertySource(locations = "classpath:test-db.properties")
@Sql(scripts = {"classpath:data-h2.sql"})
@AutoConfigureMockMvc
public class ReceiptControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getReceiptById() throws Exception {
        String token = authorize();
        MvcResult result = mockMvc.perform(get("/api/receipt/1").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ReceiptDto receipt = objectMapper.readValue(contentAsString, ReceiptDto.class);
        assertEquals(new ReceiptConverter().convert(ReceiptTestData.RECEIPT_0).toString(), receipt.toString());
    }

    @Test
    public void createReceipt() throws Exception {
        String token = authorize();
        MvcResult result = mockMvc.perform(post("/api/receipt").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ReceiptDto receipt = objectMapper.readValue(contentAsString, ReceiptDto.class);
        assertEquals(2, receipt.getCashierId());
    }

    private String authorize() throws Exception {
        String content = userJson();
        MvcResult mvcResult = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, AuthResponse.class).getAccessToken();
    }


    private String userJson() {
        try {
            return objectMapper.writeValueAsString(new LoginRequest("iam@batman.com", "bat"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getReceiptsWithPagination() throws Exception {
        String token = authorize();
        MvcResult result = mockMvc.perform(get("/api/receipt").header("Authorization", "Bearer " + token).param("page","1").param("size","2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ReceiptPageableResponse receipts = objectMapper.readValue(contentAsString, ReceiptPageableResponse.class);

        ReceiptConverter receiptConverter = new ReceiptConverter();
        ReceiptPageableResponse expected = new ReceiptPageableResponse(List.of(receiptConverter.convert(ReceiptTestData.RECEIPT_1), receiptConverter.convert(ReceiptTestData.RECEIPT_0)), 4);
        assertEquals(expected, receipts);

    }
}