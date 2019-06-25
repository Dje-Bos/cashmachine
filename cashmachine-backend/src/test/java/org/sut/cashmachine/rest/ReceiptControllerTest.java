package org.sut.cashmachine.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.ReceiptTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.converter.Converter;
import org.sut.cashmachine.rest.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
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
    private MockMvc mockMvc;
    @Autowired
    private Converter<ReceiptModel, ReceiptDTO> receiptConverter;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getReceiptById() throws Exception {
        String token = authorize();
        MvcResult result = mockMvc.perform(get("/api/receipt/1").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ReceiptDTO receipt = objectMapper.readValue(contentAsString, ReceiptDTO.class);
        ReceiptDTO expected = receiptConverter.convert(ReceiptTestData.RECEIPT_0);
        assertEquals(expected.toString(), receipt.toString());
    }

    @Test
    public void createReceipt() throws Exception {
        String token = authorize();
        MvcResult result = mockMvc.perform(post("/api/receipt").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ReceiptDTO receipt = objectMapper.readValue(contentAsString, ReceiptDTO.class);
        assertEquals("batman", receipt.getCashierName());
        assertEquals("OPENED",receipt.getStatus());
    }

    private String authorize() throws Exception {
        String content = userJson();
        MvcResult mvcResult = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, AuthResponseDTO.class).getAccessToken();
    }

    private String userJson() {
        try {
            return objectMapper.writeValueAsString(new LoginRequestDTO("iam@batman.com", "bat"));
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
        PaginationResponseDTO receipts = objectMapper.readValue(contentAsString, PaginationResponseDTO.class);

        PaginationResponseDTO<ReceiptDTO> expected = new PaginationResponseDTO<>(List.of(receiptConverter.convert(ReceiptTestData.RECEIPT_1), receiptConverter.convert(ReceiptTestData.RECEIPT_0)), 4);
        assertEquals(expected.toString(), receipts.toString());

    }

    @Test
    public void createNewEntry() throws Exception {
        String token = authorize();
        CreateReceiptEntryRequestDTO request = new CreateReceiptEntryRequestDTO("Roshed waffles", 3);
        MvcResult result = mockMvc.perform(post("/api/receipt/1").header("Authorization", "Bearer " + token).param("page","1").content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ReceiptDTO receipt = objectMapper.readValue(contentAsString, ReceiptDTO.class);
        assertEquals("2807.0", receipt.getTotal());
        assertEquals(3, receipt.getEntries().size());
        assertEquals(BigDecimal.valueOf(306.0), receipt.getEntries().get(2).getTotal());
    }

}
