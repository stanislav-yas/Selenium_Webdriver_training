package task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import task19.pages.CheckoutPage;
import task19.pages.MainPage;
import task19.pages.ProductPage;

public class LiteStore {

    public static WebDriver driver;
    private MainPage mainPage;
    private CheckoutPage checkoutPage;


    public LiteStore(){
        driver = new ChromeDriver();
        mainPage = new MainPage();
        checkoutPage = new CheckoutPage();
    }

    public void stop(){
        driver.quit();
    }

    public ProductPage clickFirstProduct(){
        return mainPage.clickProduct(0);
    }

    public CheckoutPage enterCheckout(){
        checkoutPage.open();
        return checkoutPage;
    }
}
