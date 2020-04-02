Feature: User can save a reading recommendation by title

  Scenario: saving is successful with a valid title
    Given command lisaa lukuvinkki is selected
    When title "Clean Code: A Handbook of Agile Software Craftsmanship" is entered
    Then system will respond with "Clean Code: A Handbook of Agile Software Craftsmanship lisätty!"