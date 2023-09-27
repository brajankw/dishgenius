package brajankw.dishgenius.recipe.controller;

import brajankw.dishgenius.recipe.request.RecipeRequest;
import brajankw.dishgenius.recipe.response.RecipeResponse;
import brajankw.dishgenius.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

  private final RecipeService recipeService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RecipeResponse addRecipe(@RequestBody RecipeRequest recipeRequest) {
    log.info("POST-request: creating new recipe");

    return recipeService.addRecipe(recipeRequest);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RecipeResponse updateRecipe(@PathVariable Long id,
                                     @RequestBody RecipeRequest recipeRequest) {
    log.info("PUT-request: updating recipe with id: {}", id);

    return recipeService.updateRecipe(id, recipeRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRecipe(@PathVariable Long id) {
    log.info("DELETE-request: deleting recipe with id: {}", id);
    recipeService.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RecipeResponse getRecipeById(@PathVariable Long id) {
    log.info("GET-request: getting recipe with id: {}", id);

    return recipeService.getResponseById(id);
  }
}
