package brajankw.dishgenius.recipe.repository;

import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import brajankw.dishgenius.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

  default Recipe getRecipeById(Long id) {

    return findById(id).orElseThrow(()
        -> new EntityNotFoundException(String.format("Recipe with id: %d is not found.", id)));
  }
}
