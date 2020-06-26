import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPlanets extends BaseTests {

    @Test
    public void readAllPlanets() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> planets = json.getList("results.planets");
        assertEquals(10, planets.size());
    }

    @Test
    public void readOnePlanet() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Tatooine", json.getString("name"));
        assertEquals("arid", json.getString("climate"));
        assertEquals("desert", json.getString("terrain"));
    }
}
