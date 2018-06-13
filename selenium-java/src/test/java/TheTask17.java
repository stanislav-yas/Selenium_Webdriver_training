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
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TheTask17 extends TestBase{

    @Test
    public void checkBrowserLog(){
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        Assert.assertTrue("Login failed",checkLogin("admin","admin"));
        wait.until(titleIs("Catalog | My Store"));
        //"table.dataTable tr.row td:nth-child(3)"
    }
}
