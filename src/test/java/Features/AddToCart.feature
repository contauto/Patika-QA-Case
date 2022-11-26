@AddToCartTest
Feature: Cart handling
  Scenario:Add a spoon food to cart
    Given a web browser is at the home page
    When user write spoon food to the search field and enter
    And user click the last item and verify it
    And user add the item to the cart
    And user go to the cart
    And user completes the transaction
    Then login page opens
    And the driver closes