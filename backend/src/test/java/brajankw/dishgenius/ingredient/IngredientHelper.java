package brajankw.dishgenius.ingredient;

import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.entity.enums.IngredientType;
import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;

public class IngredientHelper {

    public static String baseUrl = "/ingredients";
    private static final Double energy = 200.0;
    private static final String name = "Flour";
    private static final IngredientType type = IngredientType.OTHER;


    public static Ingredient createIngredient() {

        return Ingredient.builder()
                .energy(energy)
                .name(name)
                .ingredientType(type).build();
    }

    public static IngredientRequest createIngredientRequest() {

        return new IngredientRequest(
                name,
                type,
                energy
        );
    }

    public static IngredientResponse createIngredientResponseWithId(Long id) {

        return new IngredientResponse(
                id,
                name,
                type,
                energy
        );
    }

    public static String createNotFoundMessage(Long id) {

        return "Ingredient with id: " + id + " is not found.";
    }
}
