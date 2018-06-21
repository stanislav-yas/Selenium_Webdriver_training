package task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends Page {

    @FindBy(css = "ul.items li")
    private List<WebElement> items;

    @FindBy(css = "ul.shortcuts li")
    private List<WebElement> shortcuts;

    @FindBy(css = "div#box-checkout-summary tr")
    private List<WebElement> orderSummaryTableRows;

    public CheckoutPage(){
        pageUrl = "http://localhost/litecart/en/checkout";
    }

    public int getItemCount(){
        return items.size();
    }

    public boolean removeItem(int itemIndex){
        if(itemIndex >= items.size() || itemIndex < 0) return false;
        if(shortcuts.size() > 1) { //has shortcut
            shortcuts.get(itemIndex).click(); //click shortcut to select item
        }
        int rowsBefore = orderSummaryTableRows.size();
        items.get(itemIndex).findElement(By.cssSelector("button[name='remove_cart_item']")).click();
        wait.until(F -> orderSummaryTableRows.size() < rowsBefore);
        System.out.println("position removed from Cart");
        return true;
    }
}
