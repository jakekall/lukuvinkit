Feature: User can remove recommendation by id

  Scenario: removal is successful with valid id and confirmation
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "4" is selected
    And id "1" is entered
    And confirmation "y" is entered
    Then system will respond with "Lukuvinkki \"Learn Java in 14 Minutes (seriously)\" poistettu"
    And lukuvinkki is removed

  Scenario: removal fails with id that does not exists
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "4" is selected
    And id "2" is entered
    Then system will respond with warning "Lukuvinkkiä ei löytynyt id:llä 2"
    And lukuvinkki is not removed

  Scenario: removal fails with invalid id
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "4" is selected
    And id "a" is entered
    Then system will respond with warning "Id:n täytyy olla kokonaisluku"
    And lukuvinkki is not removed

  Scenario: removal fails when confirmation is negative
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "4" is selected
    And id "1" is entered
    And confirmation "n" is entered
    Then lukuvinkki is not removed
