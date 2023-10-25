package brajankw.dishgenius.ingredient.mapper;

import brajankw.dishgenius.ingredient.IngredientHelper;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientMapperTest {

    @InjectMocks
    private IngredientMapper ingredientMapper;

    @Mock
    private ObjectMapper mapper;

    private Ingredient ingredient;
    private IngredientRequest ingredientRequest;
    private IngredientResponse ingredientResponse;
    private final Long id = 100L;

    @BeforeEach
    void setUp() {
        ingredient = IngredientHelper.createIngredient();
        ingredientRequest = IngredientHelper.createIngredientRequest();
        ingredientResponse = IngredientHelper.createIngredientResponseWithId(id);
    }

    @Test
    void givenCorrectRequest_whenMapRequestToEntity_thenCorrect() throws JsonProcessingException {
        when(mapper.readValue(mapper.writeValueAsString(ingredientRequest), Ingredient.class))
                .thenReturn(ingredient);

        Ingredient actualIngredient = ingredientMapper.mapRequestToEntity(ingredientRequest);

        assertEquals(ingredient, actualIngredient);
    }

    @Test
    void givenCorrectEntity_whenMapEntityToResponse_thenCorrect() throws JsonProcessingException {
        when(mapper.readValue(mapper.writeValueAsString(ingredient), IngredientResponse.class))
                .thenReturn(ingredientResponse);

        IngredientResponse actualIngredientResponse  = ingredientMapper.mapEntityToResponse(ingredient);

        assertEquals(ingredientResponse, actualIngredientResponse);
    }
}