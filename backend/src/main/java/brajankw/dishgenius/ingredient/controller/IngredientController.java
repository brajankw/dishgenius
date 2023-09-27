package brajankw.dishgenius.ingredient.controller;

import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;
import brajankw.dishgenius.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/ingredients")
public class IngredientController {

  private final IngredientService ingredientService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public IngredientResponse addIngredient(@RequestBody IngredientRequest ingredientRequest) {
    log.info("POST-request: creating new ingredient");

    return ingredientService.addIngredient(ingredientRequest);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public IngredientResponse updateIngredient(@PathVariable Long id,
                                             @RequestBody IngredientRequest ingredientRequest) {
    log.info("PUT-request: updating ingredient with id: {}", id);

    return ingredientService.updateIngredient(id, ingredientRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteIngredient(@PathVariable Long id) {
    log.info("DELETE-request: deleting ingredient with id: {}", id);
    ingredientService.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public IngredientResponse getIngredientById(@PathVariable Long id) {
    log.info("GET-request: getting ingredient with id: {}", id);

    return ingredientService.getResponseById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<IngredientResponse> getAllIngredients(@PageableDefault Pageable pageable) {
    log.info("GET-request: getting all ingredients");

    return ingredientService.getAllIngredients(pageable);
  }
}
