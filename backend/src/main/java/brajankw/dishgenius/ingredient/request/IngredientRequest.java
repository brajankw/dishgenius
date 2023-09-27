package brajankw.dishgenius.ingredient.request;

import brajankw.dishgenius.ingredient.entity.enums.IngredientType;

public record IngredientRequest(

    String name,

    IngredientType ingredientType,

    Double energy
) {
}
