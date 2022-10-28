package StepDefinition;

import BaseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddItemAndVerifyAmount extends BaseClass {
    @Given("Go to the Website.")
    public void go_to_the_website() {
        setup();
    }

    @When("Navigate to search section and search for mobiles\\(any).")
    public void navigate_to_search_section_and_search_for_mobiles_any() {
        pageFactory.getaddItem().searchMobile();
    }

    @When("Select the specific mobile, capture the Name\\(full) of the product as displayed on UI.")
    public void select_the_specific_mobile_capture_the_name_full_of_the_product_as_displayed_on_ui() {
        pageFactory.getaddItem().PrintMobileName();
    }

    @When("Check for the Size Name and colors available and print the same on console.")
    public void check_for_the_size_name_and_colors_available_and_print_the_same_on_console() {
        pageFactory.getaddItem().PrintSizeAndColour();
    }

    @When("Navigate to Customer questions & answers and list top {int} questions and answers.")
    public void navigate_to_customer_questions_answers_and_list_top_questions_and_answers(Integer int1) {
        pageFactory.getaddItem().PrintQandA();
    }

    @When("Add the item to the cart, put assertion on the product added to cart.")
    public void add_the_item_to_the_cart_put_assertion_on_the_product_added_to_cart() {
        pageFactory.getaddItem().AddToCart();
    }

    @When("Navigate to Shopping Cart button, increase the order Quantity to {int}.")
    public void navigate_to_shopping_cart_button_increase_the_order_quantity_to(Integer int1) {
        pageFactory.getaddItem().ShoppingCart();
    }

    @Then("Verify the item quantity inside the cart and list the total amount for the order.")
    public void verify_the_item_quantity_inside_the_cart_and_list_the_total_amount_for_the_order() {
        pageFactory.getaddItem().VerifyProductPrice();
        browserClose();
    }

}
