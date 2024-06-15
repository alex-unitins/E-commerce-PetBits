package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.BrinquedoDTO;
import br.unitins.topicos1.dto.BrinquedoResponseDTO;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.service.BrinquedoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class BrinquedoResourceTest {
    //tests issues solution from https://stackoverflow.com/questions/62689707/authenticating-mock-user-when-testing-in-quarkus
    @Inject
    public MarcaRepository marcaRepository;
 
    @Inject
    public BrinquedoService brinquedoService;
    

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/brinquedos")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("Bolinha")));
    }
    @Test
    public void findByDescricao(){
        given()
            .when()
            .get("/brinquedos/search/descricao/para")
            .then()
            .statusCode(200)
            .body("descricao", hasItem(containsString("para")));
    }
    @Test 
    public void findByIdMarca(){
        given()
            .when()
            .get("/brinquedos/search/marca/1")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("Bolinha")));
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/brinquedos/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/brinquedos/search/nome/Bolinha")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Bolinha")));
            
    }

    @Test
    public void findByAnimal() {
        given()
            .when()
            .get("/brinquedos/search/animal/cão")
            .then()
            .statusCode(200)
            .body("animal", everyItem(containsString("cão")));
            
    }
    @Test
    @TestSecurity(user = "admin", roles = "Admin")
    public void createTest() {
        BrinquedoDTO dto = new BrinquedoDTO("testCriar", 12.2, "criar", "material de teste", Long.valueOf(2), "animalcriar", 2,1);
        
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/brinquedos")
            .then()          
            .statusCode(201)
            .body("nome", is("testCriar"))
            .body("descricao", is("criar"))
            .body("material", is("material de teste"))
            .body("animal", is("animalcriar"))
            .body("preco", is(12.2f)) 
            .body("marca.id", is(2)) 
            .body("tipoBrinquedo.id", is(2));  
            
    }

    @Test
    @TestSecurity(user = "admin", roles = "Admin")
    public void updateTest() {
        BrinquedoDTO dto = new BrinquedoDTO("testAtualizar", 10.2, "atualizar", "material de ", 1l, "animalatualizar", 1, 1);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 2)
            .put("/brinquedos/{id}")
            .then()
            .statusCode(204);
  
    }
    @Test
    public void findByTipo(){
        given()
            .when()
            .get("/brinquedos/search/tipoBrinquedo/1")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Bolinha")));
    }

    @Test
    @TestSecurity(user = "admin", roles = "Admin")
    public void deleteTest() {
        BrinquedoResponseDTO response = brinquedoService.create(new BrinquedoDTO("testDeletar", 15.2, "criar pra deletar", "material de deletar", 1L, "animaldeletar", 1, 1));
        assertNotNull(response);
        given()
            .pathParam("id", response.id())
            .when()
            .delete("/brinquedos/{id}")
            .then()
            .statusCode(204);
        assertNull(brinquedoService.findById(response.id()));
    }

}