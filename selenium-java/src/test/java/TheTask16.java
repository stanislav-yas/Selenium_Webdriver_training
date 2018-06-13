// Задание 16. Научитесь использовать облачный грид
//
//    Запустить несколько тестов в каком-нибудь облачном сервисе на выбор:
//    https://www.browserstack.com/
//    https://www.gridlastic.com/
//    https://saucelabs.com/
//    https://testingbot.com/

import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TheTask16 {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("platform","MAC");
        firefoxOptions.setCapability("build", "JUnit - Sample:MAC");
        driver = new RemoteWebDriver(
                new URL("https://stanislav158:LYwTXaH3Tpm2tzzJNnzM@hub-cloud.browserstack.com/wd/hub"),
                firefoxOptions);
    }

    @Test
    public void testSimple(){
        driver.get("http://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
