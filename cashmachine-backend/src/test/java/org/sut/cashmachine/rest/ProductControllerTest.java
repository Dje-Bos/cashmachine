package org.sut.cashmachine.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.ProductTestData;
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
import org.sut.cashmachine.rest.converter.ProductConverter;
import org.sut.cashmachine.rest.dto.AuthResponse;
import org.sut.cashmachine.rest.dto.LoginRequest;
import org.sut.cashmachine.rest.dto.ProductDto;
import org.sut.cashmachine.rest.dto.ReceiptDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindProducts() throws Exception {
        String token = authorize();
        MvcResult result = mockMvc.perform(get("/api/products").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).param("query", "pet"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<ProductDto> dtos = objectMapper.readValue(contentAsString, new TypeReference<List<ProductDto>>(){});
        assertEquals(2, dtos.size());
        ProductConverter converter = new ProductConverter();
        List<ProductDto> expected = List.of(ProductTestData.PRODUCT_0, ProductTestData.PRODUCT_1).stream().map(converter::convert).collect(Collectors.toList());
        assertEquals(expected.toString(), dtos.toString());
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
    public void getProductsByQuery() {

    }
}