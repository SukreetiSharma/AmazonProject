package StepDefinition;

import Enums.AddItemEnums;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class AddItemAndVerifyAmount extends BaseClass {
    public static Properties prop;
    String Search ="//input[@id='%s']";
    By Colour = By.xpath("(//span[@class='selection'])[1]");
    By Mobile = By.xpath("(//span[contains(text(),'Redmi 9A Sport')])[1]");
    By MobileSize = By.xpath("//p[@class='a-text-left a-size-base']");
    By Question = By.xpath("//span[@data-csa-c-id='5v3fge-m2aifc-49rfbq-uws09q']");
    By AddtoCart = By.xpath("//input[@name='submit.add-to-cart']");
    By verify = By.xpath("(//span[contains(text(),'Added to Cart')])[3]");
    By ShoppingCart = By.xpath("(//span[@class='a-button a-button-base attach-button-large attach-cart-button'])[1]");
    By QuestionLink = By.xpath("(//span[@class='celwidget']//span[@class='a-size-base'])[1]");
    By ColourName = By.xpath("(//div[@id='variation_color_name']//following::span[@class='selection'])[1]");
    By AllProductColour = By.xpath("//img[@class='imgSwatch']");
    By Quantity = By.xpath("//span[@class='a-button-text a-declarative']");
    By Value = By.xpath("//li[@aria-labelledby='quantity_4']");
    By verifyQuantity = By.xpath("//span[@class='a-dropdown-prompt']");
    By SubTotal = By.xpath("(//div[@data-name='Subtotals'])[1]");
    @Given("Go to the Website.")
    public void go_to_the_website() throws IOException {
        setup();
        FileReader reader = new FileReader("src/test/java/Data/Data.properties");
        Properties data = new Properties();
        data.load(reader);
        driver.get(data.getProperty("url"));
    }

    @Given("Navigate to search section and search for mobiles\\(any).")
    public void navigate_to_search_section_and_search_for_mobiles_any(DataTable searchData) {
        List<List<String>> data = Collections.singletonList(searchData.values());
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//Data//Data.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(String.format(Search, AddItemEnums.SEARCH.getResourcesName()))).sendKeys(data.get(0).get(0));
        driver.findElement(By.xpath(String.format(Search, AddItemEnums.SEARCHBUTTON.getResourcesName()))).click();
    }

    @Given("Select the specific mobile, print name , size , colour and Top three question and answer")
    public void select_the_specific_mobile_print_name_size_colour_and_top_question_and_answer() {
        List<WebElement> myElements = driver.findElements(Mobile);
        System.out.println("Mobile Name :");
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
        driver.findElement(Mobile).click();
        HandleWindow();
        System.out.println("Available Mobile Size :");
        String productSize = driver.findElement(MobileSize).getText();
        System.out.println(productSize);
        System.out.println("Available Mobile colour:");
        String productColour = driver.findElement(Colour).getText();
        System.out.println(productColour);
        List<WebElement> ProductsColors = driver.findElements(ColourName);
        for (WebElement allElementsColors : ProductsColors) {
            Actions action = new Actions(driver);
            WebElement element = driver.findElement(AllProductColour);
            action.moveToElement(element).perform();
            String productInfoColors = allElementsColors.getText();
            System.out.println(productInfoColors);
        }
        driver.findElement(QuestionLink).click();
        List<WebElement> myEle = driver.findElements(Question);
        System.out.println("Question and Answer :");
        for (WebElement e : myEle) {
            System.out.println(e.getText());
        }
    }

    @Given("Add the item to the cart, put assertion on the product added to cart.")
    public void add_the_item_to_the_cart_put_assertion_on_the_product_added_to_cart() {
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//Data//Data.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(AddtoCart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(verify));
        String actual = driver.findElement(verify).getText();
        Assert.isTrue(actual.equals(prop.getProperty("AddToCart")), "Expected result does not match with actual result");
    }
    @When("Navigate to Shopping Cart button, increase the order Quantity to Four.")
    public void navigate_to_shopping_cart_button_increase_the_order_quantity_to(Integer int1) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCart));
        driver.findElement(ShoppingCart).click();
        driver.findElement(Quantity).click();
        driver.findElement(Value).click();    }

    @Then("Verify the item quantity inside the cart and list the total amount for the order.")
    public void verify_the_item_quantity_inside_the_cart_and_list_the_total_amount_for_the_order() {
        String actual = driver.findElement(verifyQuantity).getText();
        Assert.isTrue(actual.equals("4"), "Expected result does not match with actual result");
        List<WebElement> myElements = driver.findElements(SubTotal);
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
        browserClose();
    }

}
