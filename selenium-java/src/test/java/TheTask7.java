import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

// Задание 7. Сделайте сценарий, проходящий по всем разделам админки
//
//     Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
//
//     1) входит в панель администратора http://localhost/litecart/admin
//     2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
//     3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)

public class TheTask7 extends TestBase{

    private int clickCount = 0;

    @Test
    public void checkMenu(){
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        String cssSelectorUL = "ul#box-apps-menu.list-vertical";      // left menu CSS Selector
        clickAllItemsWithChildren(cssSelectorUL);
        System.out.println(clickCount + " time(s) clicked");
        Assert.assertEquals("Inconsistency in the number of menu items",48,clickCount);
    }

    private void clickAllItemsWithChildren(String cssSelectorUL){
        int size = driver.findElements(By.cssSelector(cssSelectorUL + " >li")).size(); // list item count
        for(int i = 1; i <= size; i++) {
            String cssSelectorLI = cssSelectorUL + " >li:nth-child(" + i + ")";
            WebElement li = driver.findElement(By.cssSelector(cssSelectorLI)); // find n-th list item
            if(li.getAttribute("class").equals("selected")) continue;  // skip already selected item
            li.click(); clickCount++;
            System.out.println("h1 = " + driver.findElement(By.tagName("h1")).getText()); // check for 'h1' tag
            String cssSelectorULChild = cssSelectorLI + " >ul";
            if(driver.findElements(By.cssSelector(cssSelectorULChild)).size()>0){ // check for submenu existence
                clickAllItemsWithChildren(cssSelectorULChild); // to click submenu recursively
            }
        }
    }

}
