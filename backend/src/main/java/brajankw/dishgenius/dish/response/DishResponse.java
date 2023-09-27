package brajankw.dishgenius.dish.response;

import brajankw.dishgenius.comment.entity.Comment;
import brajankw.dishgenius.dish.entity.enums.DishType;
import brajankw.dishgenius.image.entity.Image;
import brajankw.dishgenius.ingredient_in_dish.entity.IngredientInDish;
import brajankw.dishgenius.recipe.entity.Recipe;
import java.util.List;

public record DishResponse(

    Long id,

    String name,

    String description,

    Double rating,

    DishType dishType,

    Recipe recipe,

    List<Comment> comments,

    List<Image> images,

    List<IngredientInDish> ingredientsInDish
) {}
