// Задание 14. Проверьте, что ссылки открываются в новом окне
//
//    Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
//    Сценарий должен состоять из следующих частей:
//    1) зайти в админку
//    2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
//    3) открыть на редактирование какую-нибудь страну или начать создание новой
//    4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -
//      они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
//    Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank".
//    Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне,
//    потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.
//    Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TheTask14 extends TestBase {

    @Test
    public void checkExtLinks(){

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        Assert.assertTrue("Login failed",checkLogin("admin","admin"));
        wait.until(titleIs("Countries | My Store"));
        driver.findElement(By.cssSelector("table.dataTable tr.row:nth-child(2) > td:nth-child(5) > a:nth-child(1)")).click();
        wait.until(titleIs("Edit Country | My Store"));
        List<WebElement> extLinks = driver.findElements(By.cssSelector("#content i.fa-external-link"));
        String parentWnd = driver.getWindowHandle();
        Set<String> oldWnds = driver.getWindowHandles();
        for (WebElement link:extLinks) {
            link.click();
            //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            String newWindowHnd = wait.until(new ExpectedCondition<String>() {
                @Override
                public String apply(WebDriver input) {
                    Set<String> newWnds = input.getWindowHandles();
                    newWnds.removeAll(oldWnds);
                    return newWnds.size()>0 ? newWnds.iterator().next() : null;
                }
            });
            driver.switchTo().window(newWindowHnd);
            wait.until(F ->  { return driver.getTitle().length()>0;}); // for Firefox: waiting for page title
            System.out.println("Visited page title is: " + driver.getTitle());
            driver.close();
            driver.switchTo().window(parentWnd);
        }
    }

}
