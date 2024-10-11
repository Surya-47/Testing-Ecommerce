Feature: User Sign-in and Delete Account

  Scenario: Sign in and delete user account
    Given I am on the login page
    When I sign in using username "qwerty" and password "Qwerty@123"
    And I sign in again
    And I navigate to "My Account"
    And I click on "Delete Account"
    And I confirm the deletion by clicking "Yes"
    Then the account should be deleted successfully
