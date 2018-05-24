import org.junit.Test;
import org.openqa.selenium.*;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TheTask7 extends TestBase{

    @Test
    public void checkMenu(){
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        WebElement menu = driver.findElement(By.cssSelector("#box-apps-menu"));
        List<WebElement> items = menu.findElements(By.cssSelector("li"));
        List<WebElement> elements = driver.findElements(By.cssSelector("#box-apps-menu li a"));
        for(WebElement element:elements){
            element.click();
        }
    }
}
