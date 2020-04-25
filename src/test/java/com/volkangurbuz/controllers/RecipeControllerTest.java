package com.volkangurbuz.controllers;

import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.services.RecipeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class RecipeControllerTest {

  @Mock RecipeService recipeService;

  RecipeController recipeController;

  @BeforeEach
  public void beforeEach() {
    MockitoAnnotations.initMocks(this);

    recipeController = new RecipeController(recipeService);
  }

  @Test
  void showById() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    when(recipeService.findById(anyLong())).thenReturn(recipe);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/recipe/show/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attributeExists("recipe"));
  }
}
