package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.RemedioDTO;
import br.unitins.topicos1.dto.RemedioResponseDTO;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.RemedioRepository;
import br.unitins.topicos1.repository.SaborRepository;
import br.unitins.topicos1.service.RemedioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class RemedioResourceTest {
    @Inject
    public SaborRepository saborRepository;
    
    @Inject
    public MarcaRepository marcaRepository;
    
    @Inject
    RemedioService remedioService;

    @Inject
    RemedioRepository remedioRepository;

    
    @Test
    public void createTest() {
        RemedioDTO dto = new RemedioDTO("Anti-pulgas", 25.00, "criar", "1 comprimido", 2L, "cão",2, 1);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/remedios")
            .then()
            .statusCode(201)
            .body("nome", is("Anti-pulgas"))
            .body("descricao", is("criar"))
            .body("animal", is("cão"))
            .body("preco", is(25.00f)) 
            .body("quantidade", is("1 comprimido"))
            .body("marca.id", is(2)) 
            .body("pesoAnimal.id", is(1))
            .body("idade.id",is(2));

    }

    @Test
    public void findByIdTest() {

        given()
            .when()
            .get("/remedios/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void updateTest() {
        RemedioDTO dto = new RemedioDTO("Anti-pulgas", 25.00, "atualizar", "1 comprimido", 2L, "cão",2, 1);
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/remedios/{id}")
            .then()
            .statusCode(204);
  
    }
    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/remedios/search/nome/Vermífugo")
            .then()
            .statusCode(200)
            .body("nome", hasItem(("Vermífugo")));
    }
    @Test 
    public void findByIdade(){
        given()
            .when()
            .get("/remedios/search/idade/2")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Anti")));
    }
    @Test
    public void findByPesoAnimal(){
        given()
            .when()
            .get("/remedios/search/pesoAnimal/2")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Anti")));
    }

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/remedios")
            .then()
            .statusCode(200)
            .body("descricao", hasItem(containsString("para")));
    }

    @Test
    public void findByAnimalTest() {
        given()
            .when()
            .get("/remedios/search/animal/cão")
            .then()
            .statusCode(200)
            .body("animal", hasItem(containsString("cão")));
    }

    @Test
    public void deleteTest() {
        RemedioResponseDTO response = remedioService.create(new RemedioDTO("Anti-pulgas", 25.00, "deletar", "1 comprimido", 2L, "cão",2, 1));
        assertNotNull(response);
        given()
            .pathParam("id", response.id())
            .when()
            .delete("/remedios/{id}")
            .then()
            .statusCode(204);
        assertNull(remedioService.findById(response.id()));
    }

    @Test
    public void findByMarcaTest() {

        given()
            .when()
            .get("/remedios/search/marca/2")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("Vermífugo")));
    }

    @Test
    public void findByDescricaoTest() {
        given()
            .when()
            .get("/remedios/search/descricao/combate")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("Vermífugo")));
    }
}
