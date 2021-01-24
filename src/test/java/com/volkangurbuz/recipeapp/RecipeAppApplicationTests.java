package com.volkangurbuz.recipeapp;

import com.volkangurbuz.converters.RecipeCommandToRecipe;
import com.volkangurbuz.converters.RecipeToRecipeCommand;
import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.repositories.RecipeRepository;
import com.volkangurbuz.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeAppApplicationTests {

  RecipeServiceImpl recipeService;

  @Mock RecipeRepository recipeRepository;

  @Mock RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock RecipeCommandToRecipe recipeCommandToRecipe;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    recipeService =
        new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  void contextLoads() {

    Recipe recipe = new Recipe();
    HashSet recHashSet = new HashSet<>();
    recHashSet.add(recipe);

    when(recipeService.getRecipes()).thenReturn(recHashSet);

    Set<Recipe> recipeSet = recipeService.getRecipes();
    assertEquals(recipeSet.size(), 1);
    // verify that the recipe repository times once, only once
    verify(recipeRepository, times(1)).findAll();
  }
}
