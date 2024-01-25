package com.pedro.calcados.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.calcados.service.MarcaService;

@WebMvcTest(controllers = MarcaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MarcaControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MarcaService marcaService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void MarcaController_Create_ReturnsCreated() throws Exception {
    // Arrange
    // Act
    // Assert
  }
}
