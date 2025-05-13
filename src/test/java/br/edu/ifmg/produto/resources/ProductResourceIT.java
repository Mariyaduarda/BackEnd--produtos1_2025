package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.entities.Product;
import br.edu.ifmg.produto.util.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import br.edu.ifmg.produto.dto.ProductDTO;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {

    // Objeto que irá fazer as requisições
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 200L;
    }

    @Test
    public void findAllShouldReturnSortedPageWhenSortByName () throws Exception {
        ResultActions result = mockMvc.perform(
                get("/product?page=0&size=10&sort=name,asc")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[0].name").value("Macbook Pro"));
        result.andExpect(jsonPath("$.content[1].name").value("PC Gamer"));
    }

    @Test
    public void updateShouldReturnDtoWhenIdExists() throws Exception {

        ProductDTO dto = Factory.createProductDTO();
        String dtoJson = objectMapper.writeValueAsString();

        String nameExpected = dto.getName();
        String descriptionExpected = dto.getDescription();

        ResultActions result = mockMvc
                .perform(put(
                "/product/{id}", existingId)
                        .content(dtoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ))

                result.andExpect(status().isOk());
                result.andExpect(jsonPath("$.id").value(existingId));
                result.andExpect(jsonPath("$.name").value(nameExpected));
                result.andExpect(jsonPath("$.description").value(descriptionExpected));
    }
    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        ProductDTO dto = Factory.createProductDTO();
        String dtoJson = objectMapper.writeValueAsString();

        String nameExpected = dto.getName();
        String descriptionExpected = dto.getDescription();

        ResultActions result = mockMvc
                .perform(put(
                        "/product/{id}", nonExistingId)
                        .content(dtoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ))

        result.andExpect(status().isNotFound());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.name").value(nameExpected));
        result.andExpect(jsonPath("$.description").value(descriptionExpected));
    }

    @Test
    public void InsertShouldReturnNewObjectWhenDataAreCorrect() throws Exception {

        //checar se os estados sao 202 created
        //checar se o id do novo produto e 26
        //checar se o nome inserido é o nome esperado
        ProductDTO dto = Factory.createProductDTO();
        String dtoJson = objectMapper.writeValueAsString();

        String nameExpected = dto.getName();
        String descriptionExpected = dto.getDescription();

        ResultActions result = mockMvc
                .perform(post(
                        "/product", existingId)
                        .content(dtoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ))

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.name").value(nameExpected));

    }

    @Test
    public void DeleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ResultActions result =
                mockMvc.perform(put(
                        put("/product/{id}", existingId)
                        .content(dtoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ));

        result.andExpect(status().isNotFound());

    }

    public void DeleteShouldReturnNotFoundWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        delete("/product/{id}", existingId)
                        );

        result.andExpect(status().isNotFound());
    }
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        ResultActions result =
                mockMvc.perform(
                        get("/product/{id}", nonExistingId)
                                .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(status().isOk());
        String resutJson =
                result.andReturn().getResponse().getContentAsString();
        System.out.println(resutJson);

        ProductDTO dto =
                objectMapper.readValue(resutJson, ProductDTO.class);
        Assertions.assertEquals(nonExistingId, dto.getId());
        Assertions.assertEquals("City of bones", dto.getId());
    }
}