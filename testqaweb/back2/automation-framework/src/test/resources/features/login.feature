@login @smokeTest
Feature: WebDriver University - Login Page

  Scenario Outline: Validate UnSuccessful Login - Unique Data
    Given I access the webdriver university login page
    When I enter a username <username>
    And I enter a password <password>
    And I click on the login button
    Then I should be presented with the unsuccessful login message
    Examples:
      | username | password |
      | Juan     | papa     |

  Scenario Outline: Validate Successful Login - Unique Data
    Given I access the webdriver university login page
    When I enter a username <username>
    And I enter a password <password>
    And I click on the login button
    Then I should be presented with the successful login message
    Examples:
      | username  | password     |
      | webdriver | webdriver123 |