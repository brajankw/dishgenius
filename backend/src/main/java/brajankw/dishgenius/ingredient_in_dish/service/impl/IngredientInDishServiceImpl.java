package brajankw.dishgenius.ingredient_in_dish.service.impl;

import brajankw.dishgenius.error_handling.exception.ValidationException;
import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.ingredient_in_dish.mapper.IngredientInDishMapper;
import brajankw.dishgenius.ingredient_in_dish.repository.IngredientInDishRepository;
import brajankw.dishgenius.ingredient_in_dish.request.IngredientInDishRequest;
import brajankw.dishgenius.ingredient_in_dish.service.IngredientInDishService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientInDishServiceImpl implements IngredientInDishService {

  private final IngredientInDishRepository ingredientInDishRepository;
  private final IngredientInDishMapper ingredientInDishMapper;

  @Override
  public IngredientInDish addIngredientToDish(Long dishId,
                                              IngredientInDishRequest ingredientInDishRequest) {
    IngredientInDish ingredientInDish =
        ingredientInDishMapper.mapRequestToEntity(dishId, ingredientInDishRequest);

    return save(ingredientInDish);
  }

  @Override
  public IngredientInDish updateIngredientInDish(Long dishId, Long id,
                                                 IngredientInDishRequest ingredientInDishRequest) {
    checkIfDishHasThisIngredient(dishId, id);
    IngredientInDish ingredientInDish =
        ingredientInDishMapper.mapRequestToEntity(dishId, ingredientInDishRequest);
    ingredientInDish.setId(id);

    return save(ingredientInDish);
  }

  @Override
  public void deleteByDishIdAndId(Long dishId, Long id) {
    IngredientInDish ingredientInDish = getById(id);
    checkIfDishHasThisIngredient(dishId, id);
    ingredientInDish.setDish(null);
    ingredientInDish.setIngredient(null);
    delete(ingredientInDish);
  }

  @Override
  public IngredientInDish getById(Long id) {
    log.info("Getting ingredient in dish with id: {}", id);

    return ingredientInDishRepository.getIngredientInDishById(id);
  }

  @Override
  @Transactional
  public IngredientInDish save(IngredientInDish ingredientInDish) {
    log.info("Saving ingredient in dish to database.");

    return ingredientInDishRepository.save(ingredientInDish);
  }

  @Override
  @Transactional
  public void delete(IngredientInDish ingredientInDish) {
    log.info("Deleting ingredient in dish with id: {}", ingredientInDish.getId());
    ingredientInDishRepository.delete(ingredientInDish);
  }

  @Override
  public void checkIfDishHasThisIngredient(Long dishId, Long ingredientInDishId) {
    IngredientInDish ingredientInDish = getById(ingredientInDishId);
    if (ingredientInDish.getDish() != null
        && !ingredientInDish.getDish().getId().equals(dishId)) {

      throw new ValidationException(
          String.format(
              "Dish with id %d doesn't have ingredient in dish with id %d.",
              dishId, ingredientInDishId));
    }
  }
}
