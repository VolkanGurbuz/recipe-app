package com.volkangurbuz.controllers;

import com.volkangurbuz.commands.RecipeCommand;
import com.volkangurbuz.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  // pathvariable annotions takes the id on the url that we can use
  @RequestMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {

    model.addAttribute("recipe", recipeService.findById(new Long(id)));

    return "recipe/show";
  }

  @RequestMapping("recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeform";
  }

  @RequestMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {

    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return "recipe/recipeform";
  }

  // after saving the saved recipe will be shown
  @PostMapping
  @RequestMapping("recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }
}
