package brajankw.dishgenius.ingredient.service;

import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientService {

  IngredientResponse addIngredient(IngredientRequest ingredientRequest);

  IngredientResponse updateIngredient(Long id, IngredientRequest ingredientRequest);

  IngredientResponse getResponseById(Long id);

  Ingredient getById(Long id);

  void delete(Ingredient ingredient);

  void deleteById(Long id);

  Ingredient save(Ingredient ingredient);

  Page<IngredientResponse> getAllIngredients(Pageable pageable);
}
