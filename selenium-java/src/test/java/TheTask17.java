// Задание 17. Проверьте отсутствие сообщений в логе браузера
//
//    Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц в учебном приложении,
//      а именно -- страниц товаров в каталоге в административной панели.
//    Сценарий должен состоять из следующих частей:
//    1) зайти в админку
//    2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
//    3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.Set;

public class TheTask17 extends TestBase{

    @Test
    public void checkBrowserLog(){
        Set<String> logTypes =  driver.manage().logs().getAvailableLogTypes();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        Assert.assertTrue("Login failed",checkLogin("admin","admin"));
        wait.until(titleIs("Catalog | My Store"));
        int itemCount = driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(3)")).size();
        Assert.assertTrue("No items in catalog", itemCount > 0);
        for (int i = 2; i < itemCount + 2 ; i++) {
            WebElement td = driver.findElement(By.cssSelector("table.dataTable tr.row:nth-child(" + i + ") td:nth-child(3)"));
            if(findElementByCssSelector(td,".fa") != null){
                continue; //skip folder item
            }
            String productName = td.getText();
            td.findElement(By.tagName("a")).click(); // goto item page
            for (String logType: logTypes) {
                LogEntries logEntries = driver.manage().logs().get(logType);
                Assert.assertFalse("There are entries in log: " + logType + " at product: " +
                        productName, logEntries.getAll().size() > 0);
            }
            driver.navigate().back();
        }
    }
}
