Feature: User can search for saved recommendations by title

  @problem
  Scenario: User successfully saves recommendation, searches for it by title and it is listed
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "2" is selected
    And command "4" is selected
    And title "Learn Java" is entered
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"