import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTests {

    protected String BASE_URL = "https://swapi.py4e.com/api/";
    protected String PEOPLE = "people";
    protected String FILMS = "films";
    protected String STARSHIPS = "starships";
    protected String PLANETS = "planets";

    protected static RequestSpecBuilder reqBuilder;
    protected static RequestSpecification reqSpec;

    @BeforeAll
    public static void beforeAll() {
        reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(ContentType.JSON);
        reqSpec = reqBuilder.build();
    }
}
