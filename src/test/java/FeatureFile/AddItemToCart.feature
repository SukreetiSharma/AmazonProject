Feature: Select one product and add it to cart then increase quantity.

  Scenario: Add Product to Cart and verify total amount.
    Given Go to the Website.
    And Navigate to search section and search for mobiles(any).
      | mobiles |
    And Select the specific mobile, print name , size , colour and Top 3 question and answer
    And Add the item to the cart, put assertion on the product added to cart.
    When Navigate to Shopping Cart button, increase the order Quantity to 4.
    Then Verify the item quantity inside the cart and list the total amount for the order.
