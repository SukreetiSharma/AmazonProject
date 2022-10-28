package PageFactory;

import Pages.AbleToAddItem;
import org.openqa.selenium.WebDriver;

public class PageFactory {
    public static WebDriver driver;
    public AbleToAddItem addItem;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }
    public AbleToAddItem getaddItem(){
        if(addItem == null){
            addItem = new AbleToAddItem(driver);
        }
        return addItem;
    }
}
