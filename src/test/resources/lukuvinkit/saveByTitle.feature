Feature: User can save a reading recommendation by title and url

  Scenario: saving is successful with a valid title, url and no description or tags
    Given command "1" is selected
    And command "2" is selected
    When title "Learn Java in 14 Minutes (seriously)" is entered
    And url "https://www.youtube.com/watch?v=RRubcjpTkks&" is entered
    And description "" is entered
    And tags "" are entered
    Then system will respond with "Learn Java in 14 Minutes (seriously) lisätty"