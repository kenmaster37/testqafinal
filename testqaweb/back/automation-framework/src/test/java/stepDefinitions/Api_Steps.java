package stepDefinitions;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import io.cucumber.java.en.*;
import static org.hamcrest.Matchers.*;

public class Api_Steps {

    Response response;

    @Given("I perform a GET request to {string}")
    public void i_perform_a_get_request_to(String url) {
        response = get(url);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should contain the title {string}")
    public void the_response_should_contain_the_title(String expectedTitle) {
        response.then().body("title", equalTo(expectedTitle));
    }
}