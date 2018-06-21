package task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {

    public static WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;


    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public ProductPage getFirstProduct(){
        return ProductPage.getProduct(0);
    }
}
