package brajankw.dishgenius.ingredient.mapper;


import brajankw.dishgenius.error_handling.exception.BadRequestException;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IngredientMapper {

  private final ObjectMapper mapper;

  public Ingredient mapRequestToEntity(IngredientRequest ingredientRequest) {
    log.info("Mapping ingredient request to comment ingredient.");
    try {

      return mapper.readValue(mapper.writeValueAsString(ingredientRequest), Ingredient.class);
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping ingredient request to ingredient entity " + e.getMessage());
    }
  }

  public IngredientResponse mapEntityToResponse(Ingredient ingredient) {
    log.info("Mapping ingredient entity to ingredient response.");
    try {

      return mapper.readValue(mapper.writeValueAsString(ingredient), IngredientResponse.class);
    } catch (JsonProcessingException e) {

      throw new BadRequestException(
          "Error while mapping ingredient request to ingredient response " + e.getMessage());
    }
  }
}
