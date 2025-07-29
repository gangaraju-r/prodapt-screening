package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Skeleton template for a controller test using MockMvc.
 * <p>
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 *
 * @CsvSource and @MethodSource for your test data.
 * <p>
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 * .andExpect(status().whateverStatusCodeYouExpect())
 * .andExpect(content().string("string-you-expect-in-response")).
 * .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // add your test cases here
    @Test
    public void testRemoveBasicWord() throws Exception {
        mockMvc.perform(get("/remove?input=eloquent"))
                .andExpect(status().isOk())
                .andExpect(content().string("loquen"));

        mockMvc.perform(get("/remove?input=country"))
                .andExpect(status().isOk())
                .andExpect(content().string("ountr"));

        mockMvc.perform(get("/remove?input=person"))
                .andExpect(status().isOk())
                .andExpect(content().string("erso"));
    }

    @Test
    public void testRemoveShortString() throws Exception {
        mockMvc.perform(get("/remove?input=ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        mockMvc.perform(get("/remove?input=xyz"))
                .andExpect(status().isOk())
                .andExpect(content().string("y"));
    }

    @Test
    public void testRemoveSpecialChars() throws Exception {
        mockMvc.perform(get("/remove?input=123%qwerty+"))
                .andExpect(status().isOk())
                .andExpect(content().string("123%qwerty"));
    }

    @Test
    public void testRemoveInvalidInputTooShort() throws Exception {
        mockMvc.perform(get("/remove?input=a"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/remove?input="))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRemoveMissingInputParam() throws Exception {
        mockMvc.perform(get("/remove"))
                .andExpect(status().isBadRequest());
    }

}
