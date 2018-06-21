package task19;

import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends Page {

    List<WebElement> items; //"ul.items li:nth-child(3)"
    List<WebElement> shortcuts; //"ul.shortcuts li:nth-child(3)"
    List<WebElement> orderSummaryTableRows; //"div#box-checkout-summary tr"

    public void removeItem(int itemIndex){

    }
}
