package brajankw.dishgenius.dish.service;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.dish.request.DishRequest;
import brajankw.dishgenius.dish.response.DishResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService {

  DishResponse createDish(DishRequest dishRequest);

  DishResponse updateDish(Long id, DishRequest dishRequest);

  Dish save(Dish dish);

  void delete(Dish dish);

  void deleteById(Long id);

  Dish getById(Long id);

  DishResponse getResponseById(Long id);

  Page<DishResponse> getAllDishesPage(Pageable pageable);
}
