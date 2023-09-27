package brajankw.dishgenius.dish.controller;

import brajankw.dishgenius.dish.request.DishRequest;
import brajankw.dishgenius.dish.response.DishResponse;
import brajankw.dishgenius.dish.service.DishService;
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
@RequestMapping("/dishes")
public class DishController {

  private final DishService dishService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DishResponse createDish(@RequestBody DishRequest dishRequest) {
    log.info("POST-request: creating new dish");

    return dishService.createDish(dishRequest);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public DishResponse updateDish(@PathVariable Long id,
                                 @RequestBody DishRequest dishRequest) {
    log.info("PUT-request: updating dish with id: {}", id);

    return dishService.updateDish(id, dishRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDish(@PathVariable Long id) {
    log.info("DELETE-request: deleting dish with id: {}", id);
    dishService.deleteById(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public DishResponse getDishById(@PathVariable Long id) {
    log.info("GET-request: getting dish with id: {}", id);

    return dishService.getResponseById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<DishResponse> getAllDishes(@PageableDefault Pageable pageable) {
    log.info("GET-request: getting all dishes");

    return dishService.getAllDishesPage(pageable);
  }
}
