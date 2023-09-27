package brajankw.dishgenius.dish.service.impl;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.dish.mapper.DishMapper;
import brajankw.dishgenius.dish.repository.DishRepository;
import brajankw.dishgenius.dish.request.DishRequest;
import brajankw.dishgenius.dish.response.DishResponse;
import brajankw.dishgenius.dish.service.DishService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

  private final DishRepository dishRepository;
  private final DishMapper dishMapper;

  @Override
  public DishResponse createDish(DishRequest dishRequest) {
    Dish dish = dishMapper.mapRequestToEntity(dishRequest);

    return dishMapper.mapEntityToResponse(save(dish));
  }

  @Override
  public DishResponse updateDish(Long id, DishRequest dishRequest) {
    dishRepository.checkIfExistById(id);
    Dish dish = dishMapper.mapRequestToEntity(dishRequest);
    dish.setId(id);

    return dishMapper.mapEntityToResponse(save(dish));
  }

  @Override
  @Transactional
  public Dish save(Dish dish) {
    log.info("Saving dish to database.");

    return dishRepository.save(dish);
  }

  @Override
  @Transactional
  public void delete(Dish dish) {
    log.info("Deleting dish with id: {}", dish.getId());
    dishRepository.delete(dish);
  }

  @Override
  public void deleteById(Long id) {
    delete(dishRepository.getDishById(id));
  }

  @Override
  public Dish getById(Long id) {
    log.info("Getting dish with id: {}", id);

    return dishRepository.getDishById(id);
  }

  @Override
  public DishResponse getResponseById(Long id) {

    return dishMapper.mapEntityToResponse(getById(id));
  }

  @Override
  public Page<DishResponse> getAllDishesPage(Pageable pageable) {
    log.info("Getting all dishes.");

    return dishRepository.findAll(pageable).map(dishMapper::mapEntityToResponse);
  }
}
