package BaseClass;

import PageFactory.PageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    protected static WebDriver driver;
    protected static PageFactory pageFactory;
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        pageFactory = new PageFactory(driver);
    }
    public static void browserClose(){
        driver.quit();
    }
}
