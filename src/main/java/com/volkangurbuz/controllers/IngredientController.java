package com.volkangurbuz.controllers;

import com.volkangurbuz.commands.IngredientCommand;
import com.volkangurbuz.domain.Recipe;
import com.volkangurbuz.services.IngredientService;
import com.volkangurbuz.services.RecipeService;
import com.volkangurbuz.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

  private final IngredientService ingredientService;
  private final RecipeService recipeService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(
      IngredientService ingredientService,
      RecipeService recipeService,
      UnitOfMeasureService unitOfMeasureService) {
    this.ingredientService = ingredientService;
    this.recipeService = recipeService;
    this.unitOfMeasureService = unitOfMeasureService;
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

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
  public String updateRecipeIngredient(
      @PathVariable String recipeId, @PathVariable String id, Model model) {
    model.addAttribute(
        "ingredient",
        ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @PostMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    log.debug("saved receipe id:" + savedCommand.getRecipeId());
    log.debug("saved ingredient id:" + savedCommand.getId());

    return "redirect:/recipe/"
        + savedCommand.getRecipeId()
        + "/ingredient/"
        + savedCommand.getId()
        + "/show";
  }
}
