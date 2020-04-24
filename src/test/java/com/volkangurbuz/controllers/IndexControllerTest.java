package com.volkangurbuz.controllers;

import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.services.RecipeService;
import org.h2.index.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
class IndexControllerTest {

  @Mock Model model;
  @Mock RecipeService recipeService;
  IndexController indexController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    indexController = new IndexController(recipeService);
  }

  @Test
  void testMockMVC() {
    // prefer to use mvc controller and it keeps we test very light and we do not need to be
    // bringing up the world in the spring context
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    try {
      mockMvc
          .perform(MockMvcRequestBuilders.get("/"))
          .andExpect(status().isOk())
          .andExpect(view().name("index"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void getIndexPage() {

    // gven
    Set<Recipe> recipeSet = new HashSet<>();
    recipeSet.add(new Recipe());
    // we added manually recipe because otherwise these object are equals technically on equals
    // method
    Recipe recipe = new Recipe();
    recipe.setId(2L);
    recipeSet.add(recipe);

    when(recipeService.getRecipes()).thenReturn(recipeSet);
    ArgumentCaptor<Set<Recipe>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);
    // when
    String viewName = indexController.getIndexPage(model);
    // then
    assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), setArgumentCaptor.capture());
    Set<Recipe> setInController = setArgumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}
