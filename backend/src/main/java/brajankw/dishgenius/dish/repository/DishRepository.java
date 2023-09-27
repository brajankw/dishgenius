package brajankw.dishgenius.dish.repository;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

  default Dish getDishById(Long id) {

    return findById(id).orElseThrow(()
        -> new EntityNotFoundException(String.format("Dish with id: %d is not found.", id)));
  }

  default void checkIfExistById(Long id) {
    if (!existsById(id)) {

      throw new EntityNotFoundException(String.format("Dish with id: %d is not found.", id));
    }
  }
}
