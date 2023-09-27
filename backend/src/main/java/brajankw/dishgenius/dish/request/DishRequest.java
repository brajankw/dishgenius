package brajankw.dishgenius.dish.request;

import brajankw.dishgenius.dish.entity.enums.DishType;

public record DishRequest(
    String name,

    String description,

    DishType dishType
) {}
