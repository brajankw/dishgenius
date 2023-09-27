package brajankw.dishgenius.dish.mapper;

import brajankw.dishgenius.dish.entity.Dish;
import brajankw.dishgenius.dish.request.DishRequest;
import brajankw.dishgenius.dish.response.DishResponse;
import brajankw.dishgenius.error_handling.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DishMapper {

  private final ObjectMapper mapper;

  public Dish mapRequestToEntity(DishRequest dishRequest) {
    log.info("Mapping dish request to dish entity.");
    try {

      return mapper.readValue(mapper.writeValueAsString(dishRequest), Dish.class);
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping dish request to dish entity " + e.getMessage());
    }
  }

  public DishResponse mapEntityToResponse(Dish dish) {
    log.info("Mapping dish entity to dish response.");
    try {

      return mapper.readValue(mapper.writeValueAsString(dish), DishResponse.class);
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping dish entity to dish response " + e.getMessage());
    }
  }
}
