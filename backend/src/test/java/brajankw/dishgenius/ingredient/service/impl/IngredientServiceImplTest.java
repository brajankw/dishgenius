package brajankw.dishgenius.ingredient.service.impl;

import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import brajankw.dishgenius.ingredient.IngredientHelper;
import brajankw.dishgenius.ingredient.entity.Ingredient;
import brajankw.dishgenius.ingredient.mapper.IngredientMapper;
import brajankw.dishgenius.ingredient.repository.IngredientRepository;
import brajankw.dishgenius.ingredient.request.IngredientRequest;
import brajankw.dishgenius.ingredient.response.IngredientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientMapper ingredientMapper;

    private Ingredient ingredient;
    private IngredientRequest ingredientRequest;
    private IngredientResponse ingredientResponse;
    private final Long id = 100L;
    private final Long invalidId = -100L;

    @BeforeEach
    void setUp() {
        ingredient = IngredientHelper.createIngredient();
        ingredientRequest = IngredientHelper.createIngredientRequest();
        ingredientResponse = IngredientHelper.createIngredientResponseWithId(id);
    }

    @Test
    void givenCorrectIngredientRequest_whenAddIngredient_thenCorrect() {
        when(ingredientMapper.mapRequestToEntity(ingredientRequest)).thenReturn(ingredient);
        when(ingredientMapper.mapEntityToResponse(ingredient)).thenReturn(ingredientResponse);
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        IngredientResponse actualIngredientResponse = ingredientService.addIngredient(ingredientRequest);

        assertEquals(ingredientResponse, actualIngredientResponse);
    }

    @Test
    void givenCorrectIngredientRequest_whenUpdateIngredient_thenCorrect() {
        when(ingredientRepository.existsById(anyLong())).thenReturn(true);
        when(ingredientMapper.mapRequestToEntity(ingredientRequest)).thenReturn(ingredient);
        when(ingredientMapper.mapEntityToResponse(ingredient)).thenReturn(ingredientResponse);
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        IngredientResponse actualIngredientResponse = ingredientService.updateIngredient(id, ingredientRequest);

        assertEquals(ingredientResponse, actualIngredientResponse);
    }

    @Test
    void givenIncorrectIngredientId_whenUpdateIngredient_thenException() {
        when(ingredientRepository.existsById(invalidId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ingredientService.updateIngredient(invalidId, ingredientRequest));

        String expectedMessage = IngredientHelper.createNotFoundMessage(invalidId);
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenCorrectId_whenGetResponseById_thenCorrect() {
        when(ingredientRepository.getIngredientById(id)).thenReturn(ingredient);
        when(ingredientMapper.mapEntityToResponse(ingredient)).thenReturn(ingredientResponse);

        IngredientResponse actualIngredientResponse = ingredientService.getResponseById(id);

        assertEquals(ingredientResponse, actualIngredientResponse);
    }

    @Test
    void givenIncorrectId_whenGetResponseById_thenException() {
        when(ingredientRepository.getIngredientById(invalidId)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> ingredientService.getResponseById(invalidId));
    }

    @Test
    void givenCorrectId_whenGetById_thenCorrect() {
        when(ingredientRepository.getIngredientById(id)).thenReturn(ingredient);

        Ingredient actualIngredient = ingredientService.getById(id);

        assertEquals(ingredient, actualIngredient);
    }

    @Test
    void givenIncorrectId_whenGetById_thenException() {
        when(ingredientRepository.getIngredientById(invalidId)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> ingredientService.getById(invalidId));
    }

    @Test
    void whenDelete_thenCorrect() {
        ingredientService.delete(ingredient);

        verify(ingredientRepository, times(1)).delete(ingredient);
    }

    @Test
    void givenCorrectId_whenDeleteById_thenCorrect() {
        when(ingredientRepository.getIngredientById(id)).thenReturn(ingredient);

        ingredientService.deleteById(id);
        verify(ingredientRepository, times(1)).delete(ingredient);
    }

    @Test
    void givenIncorrectId_whenDeleteById_thenException() {
        when(ingredientRepository.getIngredientById(invalidId)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> ingredientService.deleteById(invalidId));
    }

    @Test
    void whenSave_thenCorrect() {
        ingredientService.save(ingredient);

        verify(ingredientRepository, times(1)).save(ingredient);
    }

    @Test
    void whenGetAllIngredients_thenCorrect() {
        Page expectedPageSize = new PageImpl<>(List.of());
        when(ingredientRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(expectedPageSize);

        Page actualPageSize = ingredientService.getAllIngredients(PageRequest.of(0, 10));

        assertEquals(expectedPageSize, actualPageSize);
    }
}