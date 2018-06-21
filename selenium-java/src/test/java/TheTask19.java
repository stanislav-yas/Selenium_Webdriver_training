import org.junit.Test;

public class TheTask19 extends BaseTest {

    @Test
    public void doCheckout(){
        for (int i = 0; i < 5; i++) {
            liteStore.getFirstProduct()
                    .addProductToCart();
        }
        CheckoutPage checkout = liteStore.enterCheckout();
        while(checkout.getItemCount() > 0){
            checkout.removeItem(0);
        }
    }
}
