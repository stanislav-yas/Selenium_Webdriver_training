import org.junit.Test;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LitecartAdminTest extends TestBase {

    @Test
    public void loginToAdminTest(){
        driver.navigate().to("http://localhost/litecart/admin");
        java.util.List<WebElement> elements = driver.findElements(By.name("username")); //using findElements just for trying
        elements.get(0).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        driver.findElement(By.linkText("Orders")).click();
    }
}
