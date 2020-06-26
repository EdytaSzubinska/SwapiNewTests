import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPeople extends BaseTests {

    @Test
    public void readAllPeople() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PEOPLE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> people = json.getList("results.people");
        assertEquals(10, people.size());
    }

    @Test
    public void readOnePerson() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PEOPLE + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Luke Skywalker", json.get("name"));
        assertEquals("male", json.get("gender"));
        assertEquals("19BBY", json.get("birth_year"));
        assertEquals("blond", json.get("hair_color"));
    }
}
