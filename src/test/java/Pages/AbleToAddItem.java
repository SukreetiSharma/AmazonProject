package Pages;

import Enums.AddItemEnums;
import com.google.common.base.Verify;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class AbleToAddItem {
    WebDriver driver;
    WebDriverWait wait;
    public static Properties prop;
    String Search ="//input[@id='%s']";
    String Colour = "(//span[@class='%s'])[1]";
    String Mobile = "(//span[contains(text(),'Redmi 9A Sport')])[1]";
    String MobileSize = "//p[@class='%s']";
    String Question = "//span[@data-csa-c-id='%s']";
    String AddtoCart = "//input[@name='%s']";
    String verify = "(//span[contains(text(),'%s')])[3]";
    String ShoppingCart = "(//span[@class='%s'])[1]";
    By Quantity = By.xpath("//span[@class='a-button-text a-declarative']");
    By Value = By.xpath("//li[@aria-labelledby='quantity_4']");
    By verifyQuantity = By.xpath("//span[@class='a-dropdown-prompt']");
    By SubTotal = By.xpath("(//div[@data-name='Subtotals'])[1]");
    By MouseHovercolour = By.xpath("//img[@class='imgSwatch''];");

    public AbleToAddItem(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    public void searchMobile(){
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//Data//Data.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(String.format(Search, AddItemEnums.SEARCH.getResourcesName()))).sendKeys(prop.getProperty("SendKeys"));
        driver.findElement(By.xpath(String.format(Search, AddItemEnums.SEARCHBUTTON.getResourcesName()))).click();
    }

    public void PrintMobileName(){
        List<WebElement> myElements = driver.findElements(By.xpath(String.format(Mobile, AddItemEnums.MOBILE.getResourcesName())));
        System.out.println("Mobile Name :");
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
        driver.findElement(By.xpath(String.format(Mobile, AddItemEnums.MOBILE.getResourcesName()))).click();
    }
    public void PrintSizeAndColour(){
        HandleWindow();;
        List<WebElement> myElements = driver.findElements(By.xpath(String.format(MobileSize, AddItemEnums.MoBILESIZE.getResourcesName())));
        System.out.println("Available Mobile Size :");
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
        WebElement elem = driver.findElement(MouseHovercolour);
        Actions action = new Actions(driver);
        action.moveToElement(elem).perform();
        List<WebElement> Elements = driver.findElements(By.xpath(String.format(Colour, AddItemEnums.MOBILECOLOUR.getResourcesName())));
        System.out.println("Available Mobile colour:");
        for (WebElement ele : Elements) {
            System.out.println(ele.getText());
        }
    }

    public void PrintQandA(){
        List<WebElement> myElements = driver.findElements(By.xpath(String.format(Question, AddItemEnums.QUESTION.getResourcesName())));
        System.out.println("Question and Answer :");
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
    }

    public void AddToCart(){
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//Data//Data.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(String.format(AddtoCart, AddItemEnums.ADDTOCART.getResourcesName()))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(verify, AddItemEnums.VERIFY.getResourcesName()))));
        String actual = driver.findElement(By.xpath(String.format(verify, AddItemEnums.VERIFY.getResourcesName()))).getText();
        Assert.isTrue(actual.equals(prop.getProperty("AddToCart")), "Expected result does not match with actual result");
    }

    public void ShoppingCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ShoppingCart, AddItemEnums.SHOPPINGCART.getResourcesName()))));
        driver.findElement(By.xpath(String.format(ShoppingCart, AddItemEnums.SHOPPINGCART.getResourcesName()))).click();
        driver.findElement(Quantity).click();
        driver.findElement(Value).click();
    }

    public void VerifyProductPrice(){
        String actual = driver.findElement(verifyQuantity).getText();
        Assert.isTrue(actual.equals("4"), "Expected result does not match with actual result");
        List<WebElement> myElements = driver.findElements(SubTotal);
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
    }

    private void HandleWindow(){
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
        }
    }
}
