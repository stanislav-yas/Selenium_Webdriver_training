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
