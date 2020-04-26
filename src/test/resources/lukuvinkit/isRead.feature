Feature: User can view if saved recommendation is read and mark it is read.

  Scenario: User successfully saves recommendation and read status is shown
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "2" is selected
    And command "1" is selected
    Then system will respond with "Luettu: Ei"

  Scenario: User successfully changes recommendation read status
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "3" is selected
    And command "1" is selected
    And command "2" is selected
    And command "1" is selected
    Then system will respond with "Luettu: Kyllä"