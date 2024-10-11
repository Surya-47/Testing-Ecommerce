Feature: Product details validation on the Advantage Online Shopping website

  Scenario: Validate product details on the product page
    Given the Advantage browser is open
    When the user is on the product page
    Then the product details should be displayed correctly
