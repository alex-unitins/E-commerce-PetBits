// package br.unitins.topicos1.resource;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.Matchers.hasItem;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;

// import org.junit.jupiter.api.Test;

// import br.unitins.topicos1.dto.SaborDTO;
// import br.unitins.topicos1.dto.SaborResponseDTO;
// import br.unitins.topicos1.service.SaborService;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.core.MediaType;

// @QuarkusTest
// public class SaborResourceTest {

//     @Inject
//     public SaborService saborService;

//     @Test
//     public void findAllTest() {
//         given()
//             .when()
//             .get("/sabores")
//             .then()
//             .statusCode(200)
//             .body("nome", hasItem("frutos do mar")); 
//     }

//     @Test
//     public void findByIdTest() {
//         given()
//             .when()
//             .get("/sabores/1")
//             .then()
//             .statusCode(200)
//             .body("id", is(1));
//     }

//     @Test
//     public void createTest() {
//         SaborDTO dto = new SaborDTO("Saborzin", "forta");

//         given()
//             .contentType(MediaType.APPLICATION_JSON)
//             .body(dto)
//             .when()
//             .post("/sabores")
//             .then()
//             .statusCode(201)
//             .body("nome", is("Saborzin"))
//             .body("intensidade", is("forta"));
//     }

//     @Test
//     public void updateTest() {
//         SaborDTO dto = new SaborDTO("Tta", "forte");

//         given()
//             .contentType(MediaType.APPLICATION_JSON)
//             .body(dto)
//             .when()
//             .pathParam("id", 2)
//             .put("/sabores/{id}")
//             .then()
//             .statusCode(204);
//     }

//     @Test
//     public void deleteTest() {
//         SaborResponseDTO response = saborService.create(new SaborDTO("Nome", "qualquer"));
//         assertNotNull(response);

//         given()
//             .pathParam("id", response.id())
//             .when()
//             .delete("/sabores/{id}")
//             .then()
//             .statusCode(204);

//         assertNull(saborService.findById(response.id()));
//     }
//     @Test
//     public void findByNomeTest() {
//         given()
//             .when()
//             .get("/sabores/search/nome/frutos")
//             .then()
//             .statusCode(200)
//             .body("nome", hasItem("frutos do mar"));
//     }
// }
