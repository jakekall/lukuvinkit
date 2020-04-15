Feature: User can save a reading recommendation by title and url

  Scenario: saving a video is successful with a valid title, url and no description or tags
    Given command "1" is selected
    And command "2" is selected
    When title "Learn Java in 14 Minutes (seriously)" is entered
    And url "https://www.youtube.com/watch?v=RRubcjpTkks&" is entered
    And description "" is entered
    And tags "" are entered
    Then system will respond with "Learn Java in 14 Minutes (seriously) lisätty"

  Scenario: saving fails when trying to save a video without url
    Given command "1" is selected
    And command "2" is selected
    When title "Real Talk with Google Software Engineer" is entered
    And url "" is entered
    Then system will respond with "url ei voi olla tyhjä!"

  Scenario: saving a blogpost is successfull with a valid title, url and no description or tags
    Given command "1" is selected
    And command "3" is selected
    When title "Petri Kainulainen" is entered
    And url "https://www.petrikainulainen.net/programming/testing/writing-unit-tests-for-normal-spring-mvc-controllers-configuration/" is entered
    And description "" is entered
    And tags "" are entered
    Then system will respond with "Petri Kainulainen lisätty"

  Scenario: saving fails when trying to save a blogpost without url
    Given command "1" is selected
    And command "3" is selected
    When title "Petri Kainulainen" is entered
    And url "" is entered
    Then system will respond with "url ei voi olla tyhjä!"