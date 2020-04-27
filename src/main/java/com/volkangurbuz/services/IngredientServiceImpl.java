package com.volkangurbuz.services;

import com.volkangurbuz.commands.IngredientCommand;
import com.volkangurbuz.converters.IngredientToIngredientCommand;
import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(
      IngredientToIngredientCommand ingredientToIngredientCommand,
      RecipeRepository recipeRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if (!recipeOptional.isPresent()) {

      log.error("recipe id not founded");
    }

    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional =
        recipe.getIngredients().stream()
            .filter(ingredient -> ingredient.getId().equals(ingredientId))
            .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
            .findFirst();

    if (!ingredientCommandOptional.isPresent()) log.error("ingredient id not found");

    return ingredientCommandOptional.get();
  }
}
