package brajankw.dishgenius.recipe.request;

import brajankw.dishgenius.recipe.entity.RecipeDifficult;

public record RecipeRequest(

    Long dishId,

    String description,

    int prepareTimeMinutes,

    RecipeDifficult difficulty
) {
}
