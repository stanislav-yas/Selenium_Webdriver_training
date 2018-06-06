//  Задание 13. Сделайте сценарий работы с корзиной
//
//    Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
//    1) открыть главную страницу
//    2) открыть первый товар из списка
//    2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
//    3) подождать, пока счётчик товаров в корзине обновится
//    4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
//    5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
//    6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TheTask13 extends TestBase {

    @Test
    public void cartCheckout(){

        driver.navigate().to("http://localhost/litecart/");
        for (int i = 0; i < 3; i++) {
            wait.until(titleIs("Online Store | My Store"));
            WebElement element = findElementByCssSelector(null, "div.name"); // find first item
            Assert.assertNotNull("No products found on main page", element);
            String productName = element.getText(); // item name
            element.click();
            wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("div#box-product.box h1.title"))
                    ,productName)); // waiting for item page loading
            int quantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity"))
                    .getText()); // cart item quantity before addition
            element = findElementByCssSelector(null, ".options select");
            if(element != null){ // check for Size option
                findElementByCssSelector(element, "option[value='Small']").click();
            }
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click(); // add to cart
            wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("span.quantity")),
                    String.valueOf(quantity + 1))); // waiting for cart update
            driver.navigate().back();
        }
        driver.findElement(By.cssSelector("div#cart a.link")).click(); // click Checkout
        int itemCnt = driver.findElements(By.cssSelector("ul.items li")).size(); // cart item counter
        for (int i = 0; i < itemCnt; i++) {
            if(i == 0 && itemCnt >1){
                findElementByCssSelector(null,"ul.shortcuts > li")
                        .click(); // click first shortcut to stop items moving
            }
            int lineCnt = driver.findElements(By.cssSelector("div#box-checkout-summary tr"))
                    .size(); // line counter in Order Summary table
            findElementByCssSelector(null,"li.item:nth-child(1) button[name='remove_cart_item']")
                    .click(); // click Remove button
            // waiting for Order Summary table update (line number decrement)
            wait.until((WebDriver d) -> d.findElements(By.cssSelector("div#box-checkout-summary tr")).size() < lineCnt);
            System.out.println("items removed from cart");
        }
    }
}
