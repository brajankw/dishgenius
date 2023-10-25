package brajankw.dishgenius.shared;

import brajankw.dishgenius.DishgeniusApplication;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = DishgeniusApplication.class)
@ActiveProfiles("it")
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location=src/test/resources/application-it.yml" })
public abstract class AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    private static final PostgreSQLContainer<?> postgreslContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpassword");


    @BeforeAll
    static void setUp() {
        postgreslContainer.start();
        System.setProperty("spring.datasource.url", postgreslContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreslContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreslContainer.getPassword());
    }
}
