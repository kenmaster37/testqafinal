# language: en

Feature: DXHotels application tests

  Scenario: Successful login
    Given the user is on the login page
    When they enter valid credentials
    Then they access the system successfully

  Scenario: Failed login
    Given the user is on the login page
    When they enter invalid credentials
    Then an error message is displayed

  Scenario: Hotel reservation
    Given the user is authenticated and on the hotel search page
    When they search with the following filters:
      | checkin         | checkout        | rooms | adults | children | min_price | min_stars |
      | +2 days today   | +7 days today   | 2     | 3      | 2        | 200       | 3         |
    And they apply the selected filters
    And they select the cheapest hotel that matches the filters
    And they make the reservation
    Then the successful reservation message is validated
