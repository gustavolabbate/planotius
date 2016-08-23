Feature: LocalTable

  Scenario: get the last name based on row number
    Given I am in the local table page
    When I have the row number 2 in the table
    Then The LastName should be Doe
    Then Close everything