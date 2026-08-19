package com.family.shopping_list.controller;

import com.family.shopping_list.exception.ResourceNotFoundException;
import com.family.shopping_list.model.ShoppingItem;
import com.family.shopping_list.service.ShoppingItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingItemController.class)
public class ShoppingItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ShoppingItemService service;

    @Test
    void getAllItems_ShouldReturnListAnd200() throws Exception {
        ShoppingItem item = new ShoppingItem(1L, "Pepsi Max", 1, "Gott", false);
        when(service.getAllItems()).thenReturn(List.of(item));

        mockMvc.perform(get("/api/v1/items"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Pepsi Max"));
    }

    @Test
    void getItemById_ShouldReturn404_WhenNotFound() throws Exception {
        when(service.getItemById(10L)).thenThrow(new ResourceNotFoundException("404 Not Found"));

        mockMvc.perform(get("/api/v1/items/10"))
            .andExpect(status().isNotFound());
    }

    @Test
    void createItem_ShouldReturn400_WhenInvalidBody() throws Exception {
        ShoppingItem invalidItem = new ShoppingItem(1L, "", 0, "Gott", false);

        mockMvc.perform(post("/api/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidItem)))
            .andExpect(status().isBadRequest());
    }
}
