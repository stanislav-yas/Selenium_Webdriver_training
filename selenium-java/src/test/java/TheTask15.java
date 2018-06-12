import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class TheTask15 {

    RemoteWebDriver driver1, driver2;

    @Before
    public void before() throws Throwable {
        FirefoxOptions ffOps = new FirefoxOptions();
/*        ffOps.setCapability("version","60.0.2");
        ffOps.setCapability("platform", "windows_nt");*/
        InternetExplorerOptions ieOps = new InternetExplorerOptions();
        //ieOps.setCapability("platform", "WINDOWS");
        driver1 = new RemoteWebDriver(new URL("http://192.168.1.48:4444/wd/hub"), new ChromeOptions());
        driver2 = new RemoteWebDriver(new URL("http://192.168.1.48:4444/wd/hub"), ieOps);

        //driver1 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), ffOps);
        //driver2 = new RemoteWebDriver(new URL("http://192.168.1.48:4444/wd/hub"), ffOps);
    }

    @After
    public void after() {
        if(driver1 != null){
            driver1.quit();
        }
        if(driver2 != null){
            driver2.quit();
        }
    }

    @Test
    public void doRemote(){
        driver1.navigate().to("http://yandex.ru");
        driver2.navigate().to("http://google.com");
    }
}
