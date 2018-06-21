package task19;

import org.junit.Test;

public class TheTask19 extends TestBase {

    @Test
    public void doCheckout(){
        for (int i = 0; i < 3; i++) {
            app.getFirstProduct();
        }
    }
}
