package brajankw.dishgenius.ingredient.service.impl;

import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.mapper.IngredientMapper;
import brajankw.dishgenius.ingredient.repository.IngredientRepository;
import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;
import brajankw.dishgenius.ingredient.service.IngredientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

  private final IngredientRepository ingredientRepository;
  private final IngredientMapper ingredientMapper;

  @Override
  public IngredientResponse addIngredient(IngredientRequest ingredientRequest) {
    Ingredient ingredient = ingredientMapper.mapRequestToEntity(ingredientRequest);

    return ingredientMapper.mapEntityToResponse(save(ingredient));
  }

  @Override
  public IngredientResponse updateIngredient(Long id, IngredientRequest ingredientRequest) {
    if(!ingredientRepository.existsById(id)) {

      throw new EntityNotFoundException(String.format("Ingredient with id: %d is not found.", id));
    }
    Ingredient ingredient = ingredientMapper.mapRequestToEntity(ingredientRequest);
    ingredient.setId(id);

    return ingredientMapper.mapEntityToResponse(save(ingredient));
  }

  @Override
  public IngredientResponse getResponseById(Long id) {

    return ingredientMapper.mapEntityToResponse(getById(id));
  }

  @Override
  public Ingredient getById(Long id) {
    log.info("Getting ingredient with id: {}", id);

    return ingredientRepository.getIngredientById(id);
  }

  @Override
  @Transactional
  public void delete(Ingredient ingredient) {
    log.info("Deleting ingredient with id: {}", ingredient.getId());
    ingredientRepository.delete(ingredient);
  }

  @Override
  public void deleteById(Long id) {
    ingredientRepository.delete(getById(id));
  }

  @Override
  @Transactional
  public Ingredient save(Ingredient ingredient) {
    log.info("Saving ingredient to database.");

    return ingredientRepository.save(ingredient);
  }

  @Override
  public Page<IngredientResponse> getAllIngredients(Pageable pageable) {
    log.info("Getting all ingredients.");

    return ingredientRepository.findAll(pageable).map(ingredientMapper::mapEntityToResponse);
  }
}
