package com.volkangurbuz.controllers;

import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.services.IngredientService;
import com.volkangurbuz.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

  private final RecipeService recipeService;
  private final IngredientService ingredientService;

  public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("getting ingredient list for recipe id: " + recipeId);

    // use command object to avoid lazy load erros in thmleaf
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

    return "recipe/ingredient/list";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
  public String showRecipeIngredient(
      @PathVariable String recipeId, @PathVariable String id, Model model) {

    model.addAttribute(
        "ingredient",
        ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    return "recipe/ingredient/show";
  }
}
