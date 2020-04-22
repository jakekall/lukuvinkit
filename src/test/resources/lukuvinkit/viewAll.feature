Feature: User can browse saved lukuvinkit

  Scenario: user is able to view saved lukuvinkit
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
    When command "2" is selected
    And command "1" is selected
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"
    Then system will respond with "Url: https://www.youtube.com/watch?v=RRubcjpTkks&"
    Then system will respond with "Kuvaus: Where were you 2 years ago?"
    Then system will respond with "Tagit: [study]"

  Scenario: user is able to view saved blogpost
    Given user successfully saves a new blogpost with title "Petri Kainulainen" and url "https://www.petrikainulainen.net/programming/testing/writing-unit-tests-for-normal-spring-mvc-controllers-configuration/"
    When command "2" is selected
    And command "1" is selected
    Then system will respond with "Otsikko: Petri Kainulainen"


