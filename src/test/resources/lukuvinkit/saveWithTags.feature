Feature: User can add a description to recommendation when saving

    Scenario: User successfully saves tag with recommendation
      Given user successfully saves new lukuvinkki with title "Learn Java in 14 Minutes (seriously)" url "https://www.youtube.com/watch?v=RRubcjpTkks&" description "Where were you 2 years ago?" and tags "study"
      When command "2" is selected
      And command "1" is selected
      Then system will respond with "Tagit: [study]"