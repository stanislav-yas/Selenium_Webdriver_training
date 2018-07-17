// Задание 19. Реализовать многослойную архитектуру (Page Object)
//
//  Переделайте созданный в задании 13 сценарий для добавления товаров в корзину и удаления товаров из корзины,
//  чтобы он использовал многослойную архитектуру. А именно, выделите вспомогательные классы для работы с главной страницей
//  (откуда выбирается товар), для работы со страницей товара (откуда происходит добавление товара в корзину),
//  со страницей корзины (откуда происходит удаление), и реализуйте сценарий,
//  который не напрямую обращается к операциям Selenium, а оперирует вышеперечисленными объектами-страницами.
//
package task19;

import org.junit.Test;
import task19.pages.CheckoutPage;

public class TheTask19 extends TestBase {

    @Test
    public void doCheckout(){
        for (int i = 0; i < 5; i++) {
            liteStore.clickFirstProduct()
                    .addProductToCart();
        }
        CheckoutPage checkout = liteStore.enterCheckout();
        while(checkout.getItemCount() > 0){
            checkout.removeItem(0);
        }
    }
}
