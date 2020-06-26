import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetStarships extends BaseTests {

    @Test
    public void readAllStarships() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> films = json.getList("results.name");
        assertEquals(10, films.size());
    }

    @Test
    public void readOneStarship() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS + "/9")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Death Star", json.getString("name"));
        assertEquals("DS-1 Orbital Battle Station", json.getString("model"));
        assertEquals("Imperial Department of Military Research, Sienar Fleet Systems", json.getString("manufacturer"));
    }
}
