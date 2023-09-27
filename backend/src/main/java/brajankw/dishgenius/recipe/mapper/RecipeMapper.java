package brajankw.dishgenius.recipe.mapper;

import brajankw.dishgenius.dish.repository.DishRepository;
import brajankw.dishgenius.error_handling.exception.BadRequestException;
import brajankw.dishgenius.recipe.entity.Recipe;
import brajankw.dishgenius.recipe.request.RecipeRequest;
import brajankw.dishgenius.recipe.response.RecipeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecipeMapper {

  private final DishRepository dishRepository;
  private final ObjectMapper mapper;

  public Recipe mapRequestToEntity(RecipeRequest recipeRequest) {
    log.info("Mapping recipe request to recipe entity.");
    try {
      Recipe recipe = mapper.readValue(mapper.writeValueAsString(recipeRequest), Recipe.class);
      recipe.setDish(dishRepository.getDishById(recipeRequest.dishId()));

      return recipe;
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping recipe request to recipe entity " + e.getMessage());
    }
  }

  public RecipeResponse mapEntityToResponse(Recipe recipe) {
    log.info("Mapping recipe entity to recipe response.");
    try {

      return mapper.readValue(mapper.writeValueAsString(recipe), RecipeResponse.class).withDish(recipe.getDish().getId());
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping recipe request to recipe response " + e.getMessage());
    }
  }
}
