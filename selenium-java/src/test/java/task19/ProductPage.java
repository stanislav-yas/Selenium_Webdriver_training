package task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends Page {

    Cart cart;
    WebElement options;
    WebElement addToCartButton;

    public ProductPage(){
        super();
        PageFactory.initElements(driver, this);
        cart = new Cart();
    }

    public static ProductPage getProduct(int productIndex){
        return new ProductPage();
    }

    int addProductToCart(){
        return cart.getQuantity();
    }
}
