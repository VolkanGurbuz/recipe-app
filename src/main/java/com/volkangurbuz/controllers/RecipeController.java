package com.volkangurbuz.controllers;

import com.volkangurbuz.commands.RecipeCommand;
import com.volkangurbuz.exceptions.NotFoundException;
import com.volkangurbuz.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

  private final RecipeService recipeService;
  private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  // pathvariable annotions takes the id on the url that we can use

  @GetMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {

    model.addAttribute("recipe", recipeService.findById(new Long(id)));

    return "recipe/show";
  }

  @GetMapping("recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeform";
  }

  @GetMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return RECIPE_RECIPEFORM_URL;
  }

  // after saving the saved recipe will be shown
  // if the command failed so we have bad binding here, so our validation is failed
  // it shows the orm again but we will have that modelattribute recipe there with fields error
  // properties
  @PostMapping("recipe")
  public String saveOrUpdate(
      @Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {

    // bindingresult is result of the validation
    // checking the bindingresult and asking it if it has errors.
    if (bindingResult.hasErrors()) {

      bindingResult
          .getAllErrors()
          .forEach(
              objectError -> {
                log.debug(objectError.toString());
              });

      return RECIPE_RECIPEFORM_URL;
    }

    RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @GetMapping("recipe/{id}/delete")
  public String deleteById(@PathVariable String id) {
    recipeService.deleteById(Long.valueOf(id));

    return "redirect:/";
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFound(Exception exception) {

    log.error("Handling not found exception");
    log.error(exception.getMessage());

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exception", exception);

    modelAndView.setViewName("404error");

    return modelAndView;
  }
}
