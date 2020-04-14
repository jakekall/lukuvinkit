Feature: User can browse saved lukuvinkit

  Scenario: user is able to view saved lukuvinkit
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" and url "url" and tags "study"
    When command "2" is selected
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"