Feature: User can browse saved lukuvinkit

  Scenario: user is able to view saved lukuvinkit
    Given user successfully saves new lukuvinkki "Clean Code: A Handbook of Agile Software Craftsmanship"
    When command "2" is selected
    Then system will respond with "Clean Code: A Handbook of Agile Software Craftsmanship"