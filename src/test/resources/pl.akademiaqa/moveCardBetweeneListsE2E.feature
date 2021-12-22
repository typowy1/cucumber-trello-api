Feature: Move card between lists

  @cleanup
  Scenario: I am able to move card between lists
    Given I am authenticated to Trello
    And I create new board "MY BOARD"
    And I create list "MY FIRST LIST" on "MY BOARD"
    And I create card "MY FIRST CARD" on "MY FIRST LIST"
    And I create list "MY SECOND LIST" on "MY BOARD"
    When I move "MY FIRST CARD" to "MY SECOND LIST" list
    Then I see "MY FIRST CARD" on "MY SECOND LIST" list

    


