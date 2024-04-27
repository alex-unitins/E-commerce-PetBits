package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.RacaoDTO;
import br.unitins.topicos1.dto.RacaoResponseDTO;
import br.unitins.topicos1.repository.MarcaRepository;
import br.unitins.topicos1.repository.RacaoRepository;
import br.unitins.topicos1.repository.SaborRepository;
import br.unitins.topicos1.service.RacaoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class RacaoResourceTest {
    @Inject
    public SaborRepository saborRepository;
    
    @Inject
    public MarcaRepository marcaRepository;
    
    @Inject
    RacaoService racaoService;

    @Inject
    RacaoRepository racaoRepository;

    
    @Test
    public void createTest() {
        RacaoDTO dto = new RacaoDTO("Para gatos", 20.75, "criar", 3L,4L, "gato",3, 1);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/racoes")
            .then()
            .statusCode(201)
            .body("nome", is("Para gatos"))
            .body("descricao", is("criar"))
            .body("animal", is("gato"))
            .body("preco", is(20.75f)) 
            .body("sabor.id", is(3))
            .body("marca.id", is(4)) 
            .body("pesoProduto.id", is(3))
            .body("idade.id",is(1));

    }

    @Test
    public void findByIdTest() {

        given()
            .when()
            .get("/racoes/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void updateTest() {
        RacaoDTO dto = new RacaoDTO("Para gatos", 20.75, "atualizar", 3L,4L, "gato",3, 1);
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/racoes/{id}")
            .then()
            .statusCode(204);
  
    }
    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/racoes/search/nome/para")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("para")));
    }
    @Test 
    public void findByPesoProduto(){
        given()
            .when()
            .get("/racoes/search/pesoProduto/4")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Natural")));
    }
    @Test 
    public void findByIdade(){
        given()
            .when()
            .get("/racoes/search/idade/3")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Natural")));
    }
    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/racoes")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("gatos")));
    }
    @Test
    public void findByAnimalTest() {
        given()
            .when()
            .get("/racoes/search/animal/gato")
            .then()
            .statusCode(200)
            .body("animal", hasItem(containsString("gato")));
    }

    @Test
    public void deleteTest() {
        RacaoResponseDTO response = racaoService.create(new RacaoDTO("Para gatos", 20.75, "deletar", 3L,4L, "gato",3, 1));
        assertNotNull(response);
        given()
            .pathParam("id", response.id())
            .when()
            .delete("/racoes/{id}")
            .then()
            .statusCode(204);
        assertNull(racaoService.findById(response.id()));
    }

    @Test
    public void findByMarcaTest() {

        given()
            .when()
            .get("/racoes/search/marca/3")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("Natural")));
    }

    @Test
    public void findByDescricaoTest() {
        given()
            .when()
            .get("/racoes/search/descricao/natural")
            .then()
            .statusCode(200)
            .body("nome", hasItem(containsString("Natural")));
    }
    @Test
    public void findBySabor(){
        given()
            .when()
            .get("/racoes/search/sabor/1")
            .then()
            .statusCode(200)
            .body("nome", everyItem(containsString("Natural")));
    }
}
