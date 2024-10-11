Feature: Validate available products listed under the headphones genre

  Scenario: Verify that only available headphones are displayed in the headphones genre

    Given I am on the Home page
    When I navigate to the headphones genre page
		Then only available headphones should be displayed