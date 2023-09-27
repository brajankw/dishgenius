package brajankw.dishgenius.ingredient_in_dish.service;

import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.ingredient_in_dish.request.IngredientInDishRequest;

public interface IngredientInDishService {

  IngredientInDish addIngredientToDish(Long dishId, IngredientInDishRequest ingredientInDishRequest);

  IngredientInDish updateIngredientInDish(Long dishId, Long id,
                                      IngredientInDishRequest ingredientInDishRequest);

  void deleteByDishIdAndId(Long dishId, Long id);

  IngredientInDish getById(Long id);

  IngredientInDish save(IngredientInDish ingredientInDish);

  void delete(IngredientInDish ingredientInDish);

  void checkIfDishHasThisIngredient(Long dishId, Long ingredientInDishId);
}
