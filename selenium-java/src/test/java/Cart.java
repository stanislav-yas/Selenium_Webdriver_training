import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart extends PageBlock{

    @FindBy(css = "div#cart")
    private WebElement cart;

    public int getQuantity(){
        return Integer.parseInt(cart.findElement(By.cssSelector("span.quantity"))
                .getText());
    }

    public void clickCheckout(){
        cart.findElement(By.cssSelector("a.link")).click(); // click Checkout
    }
}
