package brajankw.dishgenius.ingredient.response;

import brajankw.dishgenius.ingredient.entity.enums.IngredientType;

public record IngredientResponse(

    Long id,

    String name,

    IngredientType ingredientType,

    Double energy
) {}
