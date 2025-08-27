@api

Feature: API Test with RestAssured

  Scenario: Validate GET request from JSONPlaceholder
    Given I perform a GET request to "https://jsonplaceholder.typicode.com/posts/1"
    Then the response status code should be 200
    And the response should contain the title "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"