Feature: Scroll and Back to Top functionality
  Scenario: Verify scrolling down and returning to the top of the page
    Given I am on the home page
    When I scroll down the page
    And I click on the back to top button
    Then I should be back at the top of the page
