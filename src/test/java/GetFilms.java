import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFilms extends BaseTests {

    @Test
    public void readAllFilms() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + FILMS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> films = json.getList("results.films");
        assertEquals(7, films.size());
    }

    @Test
    public void readOneFilm() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + FILMS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("A New Hope", json.getString("title"));
        assertEquals("George Lucas", json.getString("director"));
        assertEquals("Gary Kurtz, Rick McCallum", json.getString("producer"));
    }
}
