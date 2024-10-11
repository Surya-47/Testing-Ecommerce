package RestApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DummyJsonQuotes {
    
    private String quoteSchema;
    private String quotesSchema;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeClass
    public void setup() throws IOException {
        RestAssured.baseURI = "https://dummyjson.com";
        extent = ExtentReportsRest.getInstance();
        loadProperties();
    }
    @AfterClass
    public static void tearDown() {
        extent.flush();
    }

    
    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("AdvantageProperties.properties")) {
            properties.load(input);
            quoteSchema = properties.getProperty("quote.schema");
            quotesSchema = properties.getProperty("quotes.schema");
        }
    }
    
    @Test
    public void testGetQuotes() {
        test = extent.createTest("Test Get Quotes");

        given()
            .when()
                .get("/quotes")
            .then()
                .statusCode(200)
                .body("quotes.size()", greaterThan(0))
                .body("total", equalTo(1454))
                .body("quotes[0].id", equalTo(1))
                .body("quotes[0].quote", not(emptyString()))
                .body("quotes[0].author", not(emptyString()));
        
        test.pass("Successfully fetched all Quotes");

    }
    
    @Test
    public void testGetSpecificQuote() {
        test = extent.createTest("Test Specific Quote");

        given()
            .when()
                .get("/quotes/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("quote", not(emptyString()))
                .body("author", not(emptyString()));
        
        test.pass("Successfully fetched a single Quote");

    }
    
    @Test
    public void testQuoteSchemaValidation() {
        test = extent.createTest("Test Quotes schema");

        given()
            .when()
                .get("/quotes")
            .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(quotesSchema));
        test.pass("Successfully Validated quotes Schema");

    }

    @Test
    public void testGetSingleQuote() {
        test = extent.createTest("Test Fetching a single quote");

        given()
            .when()
                .get("/quotes/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("quote", not(emptyString()))
                .body("author", not(emptyString()));
        test.pass("Successfully fetched a single quote");

    }

    @Test
    public void testSingleQuoteSchemaValidation() {
        test = extent.createTest("Test Single quote schema");

        given()
            .when()
                .get("/quotes/1")
            .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(quoteSchema));
        test.pass("Successfully validated quote schema");

    }
    
    @Test
    public void testGetRandomQuote() {
        test = extent.createTest("Test Get RandomQuote");

        given()
            .when()
                .get("/quotes/random")
            .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("quote", not(emptyString()))
                .body("author", not(emptyString()));
        test.pass("Successfully fetched a random Quote");

    }

    @Test
    public void testGetRandomQuoteWithLength() {
        test = extent.createTest("Test Get Quote Random Quote with length");

        given()
            .when()
                .get("/quotes/random/10")
            .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("quote", not(emptyString()))
                .body("author", not(emptyString()));
        test.pass("Successfully fetched a random quote with specified length");

    }

    @Test
    public void testLimitAndSkipQuotes() {
        test = extent.createTest("Test Quotes with Limit and skip parameter");

        given()
            .queryParam("limit", 3)
            .queryParam("skip", 10)
            .log().all()  // Log the request details
            .when()
                .get("/quotes")
            .then()
                .log().all()  // Log the response details
                .statusCode(200)
                .body("quotes.size()", equalTo(3)) // Check if 3 quotes are returned
                .body("total", equalTo(1454))       // Check total number of quotes
                .body("skip", equalTo(10));         // Check that 10 items were skipped
        test.pass("Successfully fetched Quotes with limit and skip");

    }

    @Test
    public void testLimitZeroQuotes() {
        test = extent.createTest("Test Get Quotes with limit");

        given()
            .queryParam("limit", 5) // Fetch 5 quotes
            .log().all()  // Log the request details
            .when()
                .get("/quotes")
            .then()
                .log().all()  // Log the response details
                .statusCode(200)
                .body("quotes.size()", greaterThan(0)); // Check that at least some quotes are returned
        test.pass("Successfully fetched Quotes with only limit parameter");

    }
}
