package brajankw.dishgenius.ingredient_in_dish.mapper;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.dish.repository.DishRepository;
import brajankw.dishgenius.ingredient_in_dish.request.IngredientInDishRequest;
import brajankw.dishgenius.error_handling.exception.BadRequestException;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.repository.IngredientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IngredientInDishMapper {

  private final IngredientRepository ingredientRepository;
  private final DishRepository dishRepository;
  private final ObjectMapper mapper;

  public IngredientInDish mapRequestToEntity(Long dishId, IngredientInDishRequest ingredientInDishRequest) {
    log.info("Mapping ingredient in dish request to ingredient in dish entity.");
    try {
      IngredientInDish ingredientInDish = mapper.readValue(mapper.writeValueAsString(ingredientInDishRequest), IngredientInDish.class);
      Dish dish = dishRepository.getDishById(dishId);
      Ingredient ingredient = ingredientRepository.getIngredientById(ingredientInDishRequest.ingredientId());
      ingredientInDish.setIngredient(ingredient);
      ingredientInDish.setDish(dish);

      return ingredientInDish;
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping ingredient in dish request to ingredient in dish entity " + e.getMessage());
    }
  }
}
