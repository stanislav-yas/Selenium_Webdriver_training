import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductPage extends Page {

    private Cart cart;

    @FindBy(css = "button[name='add_cart_product']")
    private WebElement addToCartButton;

    @FindBy(css = "div#box-product.box h1.title")
    private WebElement h1Title;

    @FindBy(css = "select[name='options[Size]']")
    private WebElement options;

    public ProductPage(String url, String productName){
        pageUrl = url;
        open();
        wait.until(F -> h1Title.getText().equals(productName));
        cart = new Cart();
    }

    int addProductToCart(){
        int quantityBefore = cart.getQuantity();
        try{
            options.findElement(By.cssSelector("option[value='Small']")).click();
        }catch (NoSuchElementException e){}
        addToCartButton.click();
        wait.until(F -> cart.getQuantity() > quantityBefore);
        System.out.println("new Cart addition:" + cart.getQuantity());
        return cart.getQuantity();
    }
}
