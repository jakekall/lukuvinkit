Feature: User can search recommendations by type

  Scenario: User can choose to search by recommendation type
    When command "2" is selected
    And command "3" is selected
    Then system will respond with "Valitse haettava tyyppi: "

  Scenario: All recommendations of the searched type are listed
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "" and tags "study"
    And user successfully saves new lukuvinkki with title "5 Python Projects for Beginners" url "https://www.youtube.com/watch?v=1HHRWg--Ce4" description "" and tags "study"
    When command "2" is selected
    And command "3" is selected
    And command "2" is selected
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"
    And system will respond with "Otsikko: 5 Python Projects for Beginners"

  Scenario: Recommendations that are wrong type are not listed
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "" and tags "study"
    When command "2" is selected
    And command "3" is selected
    And command "1" is selected
    Then system will respond with "Lukuvinkkejä ei löytynyt"





