package com.volkangurbuz.services;

import com.volkangurbuz.commands.IngredientCommand;
import com.volkangurbuz.domain.Ingredient;

public interface IngredientService {

  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
