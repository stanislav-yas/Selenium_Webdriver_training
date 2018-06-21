import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LiteStore {

    public static WebDriver driver;
    private MainPage mainPage;
    private CheckoutPage checkoutPage;


    public LiteStore(){
        driver = new ChromeDriver();
        mainPage = new MainPage();
        checkoutPage = new CheckoutPage();
    }

    public void quit(){
        driver.quit();
    }

    public ProductPage getFirstProduct(){
        return mainPage.getProduct(0);
    }

    public CheckoutPage enterCheckout(){
        checkoutPage.open();
        return checkoutPage;
    }
}
