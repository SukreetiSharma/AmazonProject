Feature: Add multiple products to wishlist, select one product from wishlist and add it to cart.

  Scenario: Add Product to Cart and verify total amount
    Given Go to the Website.
    When Navigate to search section and search for mobiles(any).
    And Select the specific mobile, capture the Name(full) of the product as displayed on UI.
    And Check for the Size Name and colors available and print the same on console.
    And Navigate to Customer questions & answers and list top 3 questions and answers.
    And  Add the item to the cart, put assertion on the product added to cart.
    And  Navigate to Shopping Cart button, increase the order Quantity to 4.
    Then Verify the item quantity inside the cart and list the total amount for the order.