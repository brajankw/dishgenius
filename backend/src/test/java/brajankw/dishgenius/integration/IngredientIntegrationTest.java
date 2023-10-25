package brajankw.dishgenius.integration;

import brajankw.dishgenius.ingredient.mapper.IngredientMapper;
import brajankw.dishgenius.ingredient.repository.IngredientRepository;
import brajankw.dishgenius.shared.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static brajankw.dishgenius.ingredient.IngredientHelper.baseUrl;
import static brajankw.dishgenius.ingredient.IngredientHelper.createIngredientRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IngredientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @Transactional
    public void givenCorrectIngredientRequest_whenCreateIngredient_thenCorrect() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post(baseUrl)
                        .content(mapper.writeValueAsString(createIngredientRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
