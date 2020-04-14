Feature: User can remove recommendation by index

  Scenario: removal is successful with valid index and confirmation
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" and url "url"
    When command "3" is selected
    And index "1" is entered
    And confirmation "y" is entered
    Then system will respond with "Lukuvinkki \"Learn Java in 14 Minutes (seriously)\" poistettu"
    And lukuvinkki is removed

  Scenario: removal fails with index that does not exists
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" and url "url"
    When command "3" is selected
    And index "2" is entered
    Then system will respond with warning "Indeksin täytyy olla välillä 1-1"
    And lukuvinkki is not removed

  Scenario: removal fails with invalid index
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" and url "url"
    When command "3" is selected
    And index "a" is entered
    Then system will respond with warning "Indeksin täytyy olla kokonaisluku"
    And lukuvinkki is not removed

  Scenario: removal fails when confirmation is negative
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" and url "url"
    When command "3" is selected
    And index "1" is entered
    And confirmation "n" is entered
    Then lukuvinkki is not removed
