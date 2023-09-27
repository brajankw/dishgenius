package brajankw.dishgenius.ingredient_in_dish.controller;

import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.ingredient_in_dish.request.IngredientInDishRequest;
import brajankw.dishgenius.ingredient_in_dish.service.IngredientInDishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/dishes")
public class IngredientInDishController {

  private final IngredientInDishService ingredientInDishService;

  @PostMapping("/{dishId}/ingredients")
  @ResponseStatus(HttpStatus.CREATED)
  public IngredientInDish addIngredientToDish
      (@PathVariable Long dishId,
       @RequestBody IngredientInDishRequest ingredientInDishRequest) {
    log.info("POST-request: adding ingredient to dish with id: {}", dishId);

    return ingredientInDishService.addIngredientToDish(dishId, ingredientInDishRequest);
  }

  @PutMapping("/{dishId}/ingredients/{id}")
  @ResponseStatus(HttpStatus.OK)
  public IngredientInDish updateIngredientInDish
      (@PathVariable Long dishId, @PathVariable Long id,
       @RequestBody IngredientInDishRequest ingredientInDishRequest) {
    log.info("PUT-request: updating ingredient in dish with id: {}", dishId);

    return ingredientInDishService.updateIngredientInDish(dishId, id, ingredientInDishRequest);
  }

  @DeleteMapping("/{dishId}/ingredients/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public IngredientInDish deleteIngredientInDish
      (@PathVariable Long dishId, @PathVariable Long id) {
    log.info("DELETE-request: deleting ingredient from dish with id: {}", dishId);
    ingredientInDishService.deleteByDishIdAndId(dishId, id);
  }
}
