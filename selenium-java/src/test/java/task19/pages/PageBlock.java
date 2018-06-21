package task19.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import task19.LiteStore;

public class PageBlock {

    protected WebDriver driver;
    protected WebDriverWait wait;

    PageBlock(){
        driver = LiteStore.driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
}
