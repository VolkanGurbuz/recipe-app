package com.volkangurbuz.services;

import com.volkangurbuz.commands.RecipeCommand;
import com.volkangurbuz.domain.Recipe;

import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long id);

  RecipeCommand saveRecipeCommand(RecipeCommand command);

  RecipeCommand findCommandById(Long ids);

  void deleteById(Long id);
}
