package task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends Page {

    Cart cart;
    List<WebElement> products;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public ProductPage getProduct(int productIndex){
        return null;
    }


}
