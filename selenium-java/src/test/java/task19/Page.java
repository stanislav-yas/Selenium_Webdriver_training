package task19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver = Application.driver;
    protected WebDriverWait wait = new WebDriverWait(driver, 10);
}
