Feature: User can search reading recommendations by typing a tag

  Scenario: All recommendations that include the tag being searched are listed
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "" and tags "study"
    And user successfully saves new lukuvinkki with title "5 Python Projects for Beginners" url "https://www.youtube.com/watch?v=1HHRWg--Ce4" description "" and tags "study"
    When command "2" is selected
    And command "2" is selected
    And searchable tag "study" is entered
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"
    And system will respond with "Otsikko: 5 Python Projects for Beginners"

  Scenario: Recommendations that do not include the tag are not listed
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "" and tags "study"
    When command "2" is selected
    And command "2" is selected
    And searchable tag "entertainment" is entered
    Then system will respond with "Lukuvinkkejä ei löytynyt"

  Scenario: All recommendations that include the tag being searched are listed even when recommendation has multiple tags
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "" and tags "study, work, java"
    And user successfully saves new lukuvinkki with title "5 Python Projects for Beginners" url "https://www.youtube.com/watch?v=1HHRWg--Ce4" description "" and tags "study, python"
    When command "2" is selected
    And command "2" is selected
    And searchable tag "study" is entered
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"
    And system will respond with "Otsikko: 5 Python Projects for Beginners"

  Scenario: Recommendation's every tag is listed if one of them matches the tag being searched
    Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "" and tags "study, work, java"
    When command "2" is selected
    And command "2" is selected
    And searchable tag "study" is entered
    Then system will respond with "Otsikko: Learn Java in 14 Minutes (seriously)"
    And system will respond with "Tagit: [study, work, java]"

