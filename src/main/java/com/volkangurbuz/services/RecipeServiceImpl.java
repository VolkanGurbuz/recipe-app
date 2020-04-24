package com.volkangurbuz.services;

import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Set<Recipe> getRecipes() {
    // log Slf4f
    log.debug("I am in the service");
    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
    return recipeSet;
  }

  public Recipe findById(Long id) {
    Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

    if (!optionalRecipe.isPresent()) {
      throw new RuntimeException("Recipe not found");
    }
    return optionalRecipe.get();
  }
}
