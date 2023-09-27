package brajankw.dishgenius.ingredient_in_dish.repository;

import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientInDishRepository extends JpaRepository<IngredientInDish, Long> {

  default IngredientInDish getIngredientInDishById(Long id) {

    return findById(id).orElseThrow(()
        -> new EntityNotFoundException(String.format("Ingredient in dish with id: %d is not found.", id)));
  }
}
