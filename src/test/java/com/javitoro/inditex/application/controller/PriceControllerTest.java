package com.javitoro.inditex.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String performRequest(String date, int productId, int brandId) {
        try {
            MvcResult result = mockMvc.perform(get("/api/prices")
                            .param("applicationDate", date)
                            .param("productId", String.valueOf(productId))
                            .param("brandId", String.valueOf(brandId)))
                    .andExpect(status().isOk())
                    .andReturn();

            return result.getResponse().getContentAsString();

        } catch (Exception e) {
            fail("Error en la petición: " + e.getMessage());
            return null;
        }
    }

    @Test
    void testCase1() {
        String response = performRequest("2020-06-14 10:00:00", 35455, 1);
        assertNotNull(response);
        assertTrue(response.contains("\"priceList\":1"));
        assertTrue(response.contains("\"price\":35.50"));
    }

    @Test
    void testCase2() {
        String response = performRequest("2020-06-14 16:00:00", 35455, 1);
        assertNotNull(response);
        assertTrue(response.contains("\"priceList\":2"));
        assertTrue(response.contains("\"price\":25.45"));
    }

    @Test
    void testCase3() {
        String response = performRequest("2020-06-14 21:00:00", 35455, 1);
        assertNotNull(response);
        assertTrue(response.contains("\"priceList\":1"));
        assertTrue(response.contains("\"price\":35.50"));
    }

    @Test
    void testCase4() {
        String response = performRequest("2020-06-15 10:00:00", 35455, 1);
        assertNotNull(response);
        assertTrue(response.contains("\"priceList\":3"));
        assertTrue(response.contains("\"price\":30.50"));
    }

    @Test
    void testCase5() {
        String response = performRequest("2020-06-16 21:00:00", 35455, 1);
        assertNotNull(response);
        assertTrue(response.contains("\"priceList\":4"));
        assertTrue(response.contains("\"price\":38.95"));
    }
}
