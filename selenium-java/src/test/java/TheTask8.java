import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.List;

//Задание 8. Сделайте сценарий, проверяющий наличие стикеров у товаров
//
//    Сделайте сценарий, проверяющий наличие стикеров у всех товаров в учебном приложении litecart на главной странице.
//    Стикеры -- это полоски в левом верхнем углу изображения товара, на которых написано New или Sale или что-нибудь другое.
//    Сценарий должен проверять, что у каждого товара имеется ровно один стикер.

public class TheTask8 extends TestBase{

    @Test
    public void checkForStickersTest(){
        driver.navigate().to("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        for(WebElement product:products){
            List<WebElement> stickers = product.findElements(By.cssSelector("div.sticker"));
            String productName = product.findElement(By.cssSelector("div.name")).getText();
            Assert.assertFalse("Sticker is absent at product " + productName, stickers.size() == 0);
            Assert.assertFalse("More then 1 sticker at product " + productName, stickers.size() > 1);
            String stickerName = stickers.get(0).getText();
            Assert.assertFalse("Empty name of sticker at product " + productName, (stickerName.isEmpty()));
        }
    }
}
