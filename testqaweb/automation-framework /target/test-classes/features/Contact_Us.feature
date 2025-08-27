@contact-us @smokeTest
Feature: WebDriver University - Contact Us Page

  Scenario Outline: Validate Successful Submission
    Given I access the webdriver university contact us page
    When I enter a specific first name <first_name>
    And I enter a specific last name <last_name>
    And I enter a specific email address <email_address>
    And I enter a specific comment <comment>
    And I click on the submit button
    Then I should be presented with a successful contact us submission message
    Examples:
      | first_name | last_name | email_address | comment  |
      | Maria     | Adrian     | mj@g.com   | "Comentario Ok" |
      | Maria1    | Adrian1    | mj@g1.com | "Comentario Ok 1" |

  Scenario Outline: Validate unSuccessful Submission
    Given I access the webdriver university contact us page
    When I enter a specific first name <first_name>
    And I enter a specific last name <last_name>
    And I enter a specific email address <email_address>
    And I enter a specific comment <comment>
    And I click on the submit button
    Then I should be presented with a unSuccessful contact us submission message
    Examples:
      | first_name | last_name | email_address | comment  |
      | Maria     | Adrian     | mj@g.com1   | "Comentario Ok" |
      | Maria1    | Adrian1    | mj@g1.com1 | "Comentario Ok 1" |