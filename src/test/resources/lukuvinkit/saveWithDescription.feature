Feature: User can add a description to recommendation when saving

  Scenario: User successfully saves description with recommendation
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    And command "2" is selected
    And command "1" is selected
    And system will respond with "Kuvaus: Where were you 2 years ago?"

  Scenario: User fails to save new podcast without a description
    Given command "1" is selected
    And command "4" is selected
    When title "My first podcast" is entered
    And url "https://podcast.com" is entered
    And description "" is entered
    Then system will respond with "kuvaus ei voi olla tyhjä!"