package brajankw.dishgenius.ingredient.repository;

import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

  default Ingredient getIngredientById(Long id) {

    return findById(id).orElseThrow(()
        -> new EntityNotFoundException(String.format("Ingredient with id: %d is not found.", id)));
  }
}
