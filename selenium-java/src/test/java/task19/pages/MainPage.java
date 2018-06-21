package task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends Page {

    private Cart cart;

    @FindBy(css = "li.product")
    private List<WebElement> products;

    public MainPage(){
        pageUrl = "http://localhost/litecart/en/";
        cart = new Cart();
    }

    public ProductPage clickProduct(int productIndex){
        open();
        if(products.size() > 0 && productIndex >= 0 && productIndex < products.size()){
            WebElement productBlock = products.get(productIndex);
            String productPageUrl = productBlock.findElement(By.cssSelector("a.link")).getAttribute("href");
            String productName = productBlock.findElement(By.cssSelector("div.name")).getText();
            return new ProductPage(productPageUrl, productName);
        }else{
            return null;
        }
    }
}
