package RestApi;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath; 

public class DummyJsonProducts {

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
        extent = ExtentReportsRest.getInstance();
    }

    @AfterClass
    public static void tearDown() {
        extent.flush();
    }

    // Test case 1 : Get all products
    @Test
    public void testGetProducts() {
        test = extent.createTest("Test Get Products");
        
        given()
            .when()
            .get("/products")
            .then()
                .statusCode(200) // Validate status code
                .body(matchesJsonSchemaInClasspath("schema.json")) // Validate against the schema
                .body("products", is(not(empty()))) // Validate that the products is not empty
                .log().all(); // Log the entire response for debugging

        test.pass("Successfully fetched all products");
    }

    // Test case 2 : Get a single product
    @Test
    public void testGetSingleProduct() {
        test = extent.createTest("Test Get Single Product");
        String expectedTitle = "Essence Mascara Lash Princess"; 

        given()
            .when()
            .get("/products/1")
            .then()
                .statusCode(200) // Validate status code
                .body("title", equalTo(expectedTitle)) // Validate that the product has the expected title
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully fetched single product with expected title");
    }

    // Test case 3 : Search products
    @Test
    public void testSearchProducts() {
        test = extent.createTest("Test Search Products");

        given()
            .when()
            .get("/products/search?q=phone")
            .then()
                .statusCode(200) // Validate status code
                .body(matchesJsonSchemaInClasspath("schema.json")) // Validate against the schema
                .body("products", is(not(empty()))) // Validate that the products array is not empty
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully searched products");
    }

    // Test case 4 : Get products with limit and skip 
    @Test
    public void testFetchLimitProducts() {
        test = extent.createTest("Test Fetch Limit Products");
        int limit = 10; // Number of items per request
        int skip = 10;  

        given()
            .when()
            .get("/products?limit=" + limit + "&skip=" + skip + "&select=title,price")
            .then()
                .statusCode(200) // Validate status code
                .body(matchesJsonSchemaInClasspath("schema.json")) // Validate against the schema
                .body("products", is(not(empty()))) // Validate that the products array is not empty
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully fetched products with limit and skip");
    }
    
    // Test case 5 : Sort products
    @Test
    public void testFetchSortedProducts() {
        test = extent.createTest("Test Fetch Sorted Products");

        given()
            .when()
            .get("/products?sortBy=title&order=asc")
            .then()
                .statusCode(200) // Validate status code
                .body(matchesJsonSchemaInClasspath("schema.json")) // Validate against the schema
                .body("products", is(not(empty()))) // Validate that the products is not empty
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully fetched sorted products");
    }
    
    // Test case 6 : Get all products categories
    @Test
    public void testFetchProductCategories() {
        test = extent.createTest("Test Fetch Product Categories");

        given()
            .when()
            .get("/products/categories")
            .then()
                .statusCode(200) // Validate status code
                .body("size()", greaterThan(0)) // Validate that the categories is not empty
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully fetched product categories");
    }
    
    // Test case 7 : Get products category list
    @Test
    public void testFetchCategoryList() {
        test = extent.createTest("Test Fetch Category List");

        given()
            .when()
            .get("/products/category-list")
            .then()
                .statusCode(200) // Validate status code
                .body("size()", greaterThan(0)) // Check that the category list is not empty
                .log().all(); // Log the entire response
        test.pass("Successfully fetched category list");
    }

    // Test case 8 : Get products by a category
    @Test
    public void testFetchSmartphones() {
        test = extent.createTest("Test Fetch Smartphones");

        given()
            .when()
            .get("/products/category/smartphones")
            .then()
                .statusCode(200) // Validate status code
                .body(matchesJsonSchemaInClasspath("schema.json")) // Validate against the schema
                .body("products", is(not(empty()))) // Validate that the products is not empty
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully fetched smartphones");
    }
    
    // Test case 9 : Add a new product
    @Test
    public void testAddProduct() {
        test = extent.createTest("Test Add Product");
        String newProductJson = "{ \"title\": \"BMW Pencil\", \"brand\": \"BMW\", \"category\": \"Stationery\", \"description\": \"A premium pencil from BMW.\", \"price\": 1.99, \"stock\": 100, \"availabilityStatus\": \"in stock\" }"; 

        given()
            .header("Content-Type", "application/json")
            .body(newProductJson)
            .when()
            .post("/products/add")
            .then()
                .statusCode(201) // Validate status code
                .body("title", equalTo("BMW Pencil")) // Validate the product title
                .log().all(); // Log the entire response for debugging
        test.pass("Successfully added a new product");
    }
    
    // Test case 10 : Update a product
    @Test
    public void testUpdateProductTitle() {
        test = extent.createTest("Test Update Product Title");
        String requestBody = "{\"title\": \"iPhone Galaxy +1\"}";

        given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .put("/products/1")
            .then()
                .statusCode(200) // Validate status code
                .body("title", equalTo("iPhone Galaxy +1")) // Validate the updated title
                .log().all(); // Log the entire response
        test.pass("Successfully updated product title");
    }
    
    // Test case 11 : Delete a product
    @Test
    public void testDeleteProduct() {
        test = extent.createTest("Test Delete Product");

        given()
            .when()
            .delete("/products/1")
            .then()
                .statusCode(200) // Validate status code for successful deletion
                .log().all(); // Log the entire response
        test.pass("Successfully deleted product");
    }
}
