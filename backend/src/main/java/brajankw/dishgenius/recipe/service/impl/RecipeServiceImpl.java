package brajankw.dishgenius.recipe.service.impl;

import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import brajankw.dishgenius.recipe.entity.Recipe;
import brajankw.dishgenius.recipe.mapper.RecipeMapper;
import brajankw.dishgenius.recipe.repository.RecipeRepository;
import brajankw.dishgenius.recipe.request.RecipeRequest;
import brajankw.dishgenius.recipe.response.RecipeResponse;
import brajankw.dishgenius.recipe.service.RecipeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeMapper recipeMapper;

  @Override
  public RecipeResponse addRecipe(RecipeRequest recipeRequest) {
    Recipe recipe = recipeMapper.mapRequestToEntity(recipeRequest);

    return recipeMapper.mapEntityToResponse(save(recipe));
  }

  @Override
  public RecipeResponse updateRecipe(Long id, RecipeRequest recipeRequest) {
    if (!recipeRepository.existsById(id)) {

      throw new EntityNotFoundException(String.format("Recipe with id: %d is not found.", id));
    }

    Recipe recipe = recipeMapper.mapRequestToEntity(recipeRequest);
    recipe.setId(id);

    return recipeMapper.mapEntityToResponse(save(recipe));
  }

  @Override
  public RecipeResponse getResponseById(Long id) {

    return recipeMapper.mapEntityToResponse(getById(id));
  }

  @Override
  public Recipe getById(Long id) {
    log.info("Getting recipe with id: {}", id);

    return recipeRepository.getRecipeById(id);
  }

  @Override
  @Transactional
  public void delete(Recipe recipe) {
    log.info("Deleting recipe with id: {}", recipe.getId());
    recipe.setDish(null);
    recipeRepository.delete(recipe);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(id));
  }

  @Override
  @Transactional
  public Recipe save(Recipe recipe) {
    log.info("Saving comment to database.");

    return recipeRepository.save(recipe);
  }
}
