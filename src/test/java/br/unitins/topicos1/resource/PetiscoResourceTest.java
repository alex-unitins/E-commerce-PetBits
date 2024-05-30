// package br.unitins.topicos1.resource;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.containsString;
// import static org.hamcrest.CoreMatchers.everyItem;
// import static org.hamcrest.Matchers.hasItem;
// import static org.hamcrest.Matchers.is;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.dto.PetiscoDTO;
// import br.unitins.topicos1.dto.PetiscoResponseDTO;
// import br.unitins.topicos1.repository.MarcaRepository;
// import br.unitins.topicos1.repository.PetiscoRepository;
// import br.unitins.topicos1.repository.SaborRepository;
// import br.unitins.topicos1.service.PetiscoService;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.core.MediaType;

// @QuarkusTest
// public class PetiscoResourceTest {
//     @Inject
//     public SaborRepository saborRepository;
    
//     @Inject
//     public MarcaRepository marcaRepository;
    
//     @Inject
//     PetiscoService petiscoService;

//     @Inject
//     PetiscoRepository petiscoRepository;

    
//     @Test
//     public void createTest() {
//         PetiscoDTO dto = new PetiscoDTO("PetiscoTest", 10.0, "criar",1 ,  1L,1L,"Cachorro",1);

//         given()
//             .contentType(MediaType.APPLICATION_JSON)
//             .body(dto)
//             .when()
//             .post("/petiscos")
//             .then()
//             .statusCode(201)
//             .body("nome", is("PetiscoTest"))
//             .body("descricao", is("criar"))
//             .body("unidades", is(1))
//             .body("animal", is("Cachorro"))
//             .body("preco", is(10.0f)) 
//             .body("sabor.id", is(1))
//             .body("marca.id", is(1)) 
//             .body("pesoProduto.id", is(1));

//     }
//     @Test
//     public void findBySabor(){
//         given()
//             .when()
//             .get("/petiscos/search/sabor/3")
//             .then()
//             .statusCode(200)
//             .body("nome", everyItem(containsString("Dentinhos")));
//     }

//     @Test
//     public void findByIdTest() {

//         given()
//             .when()
//             .get("/petiscos/1")
//             .then()
//             .statusCode(200)
//             .body("id", is(1));
//     }
//     @Test 
//     public void findByPesoProduto(){
//         given()
//             .when()
//             .get("/petiscos/search/pesoProduto/2")
//             .then()
//             .statusCode(200)
//             .body("nome", everyItem(containsString("Dentinhos")));
//     }

//     @Test
//     public void updateTest() {
//         PetiscoDTO dto = new PetiscoDTO("PetiscoTest", 10.0, "deletar",2 ,  1L,1L,"gato",1);
//         given()
//             .contentType(MediaType.APPLICATION_JSON)
//             .body(dto)
//             .when()
//             .pathParam("id", 1)
//             .put("/petiscos/{id}")
//             .then()
//             .statusCode(204);
  
//     }
//     @Test
//     public void findByNomeTest() {
//         given()
//             .when()
//             .get("/petiscos/search/nome/Dentinhos")
//             .then()
//             .statusCode(200)
//             .body("nome", hasItem(containsString("Dentinhos")));
//     }
//     @Test
//     public void findAllTest() {
//         given()
//             .when()
//             .get("/petiscos")
//             .then()
//             .statusCode(200)
//             .body("nome", hasItem(containsString("Dentinhos")));
//     }
//     @Test
//     public void findByAnimalTest() {
//         given()
//             .when()
//             .get("/petiscos/search/animal/gato")
//             .then()
//             .statusCode(200)
//             .body("animal", hasItem(containsString("gato")));
//     }

//     @Test
//     public void deleteTest() {
//         PetiscoResponseDTO response = petiscoService.create(new PetiscoDTO("PetiscoTest", 10.0, "deletar",2 ,  1L,1L,"gato",1));
//         assertNotNull(response);
//         given()
//             .pathParam("id", response.id())
//             .when()
//             .delete("/petiscos/{id}")
//             .then()
//             .statusCode(204);
//         assertNull(petiscoService.findById(response.id()));
//     }

//     @Test
//     public void findByMarcaTest() {

//         given()
//             .when()
//             .get("/petiscos/search/marca/4")
//             .then()
//             .statusCode(200)
//             .body("nome", hasItem(containsString("Dentinhos")));
//     }

//     @Test
//     public void findByDescricaoTest() {
//         given()
//             .when()
//             .get("/petiscos/search/descricao/dental")
//             .then()
//             .statusCode(200)
//             .body("nome", hasItem(containsString("Dentinhos")));
//     }
// }
