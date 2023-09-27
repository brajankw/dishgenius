package brajankw.dishgenius.recipe.response;

import brajankw.dishgenius.recipe.entity.RecipeDifficult;

public record RecipeResponse(

    Long id,

    Long dishId,

    String description,

    int prepareTimeMinutes,

    RecipeDifficult difficulty
) {

  public RecipeResponse withDish(Long dishId) {

    return new RecipeResponse(
        id(),
        dishId,
        description(),
        prepareTimeMinutes(),
        difficulty()
    );
  }
}
