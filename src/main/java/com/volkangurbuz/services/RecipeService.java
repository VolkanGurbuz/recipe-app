package com.volkangurbuz.services;

import com.volkangurbuz.domain.Recipe;

import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long id);
}
