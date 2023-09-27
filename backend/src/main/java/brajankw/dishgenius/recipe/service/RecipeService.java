package brajankw.dishgenius.recipe.service;

import brajankw.dishgenius.recipe.entity.Recipe;
import brajankw.dishgenius.recipe.request.RecipeRequest;
import brajankw.dishgenius.recipe.response.RecipeResponse;

public interface RecipeService {

  RecipeResponse addRecipe(RecipeRequest recipeRequest);

  RecipeResponse updateRecipe(Long id,  RecipeRequest recipeRequest);

  RecipeResponse getResponseById(Long id);

  Recipe getById(Long id);

  void delete(Recipe recipe);

  void deleteById(Long id);

  Recipe save(Recipe recipe);
}
