// Задание 15. Постройте небольшой грид
//
//    Установите виртуальную машину, внутри которой работает Windows, и создайте грид, который состоит из диспетчера,
//      работающего на вашей основной машине, и двух узлов -- один тоже на основной машине, а другой внутри виртуальной машины.
//    Настройте узлы так, чтобы в виртуальной машине был доступен браузер Internet Explorer, а на основной машине, наоборот, он был недоступен.
//    Попробуйте запустить какие-нибудь тесты удалённо на этом гриде, указывая разные браузеры, и убедитесь,
//     что Internet Explorer действительно запускается внутри виртуальной машины, а другие браузеры, наоборот, на вашей основной машине.

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class TheTask15 {

    RemoteWebDriver driver1, driver2, driver3;

    @Before
    public void before() throws Throwable {
        driver1 = new RemoteWebDriver(new URL("http://192.168.1.48:4444/wd/hub"), new ChromeOptions());
        driver2 = new RemoteWebDriver(new URL("http://192.168.1.48:4444/wd/hub"), new FirefoxOptions());
        driver3 = new RemoteWebDriver(new URL("http://192.168.1.48:4444/wd/hub"), new InternetExplorerOptions());
    }

    @Test
    public void doRemote(){
        driver1.navigate().to("http://yandex.ru");
        driver2.navigate().to("http://google.com");
        driver3.navigate().to("https://selenium2.ru/");
    }

    @After
    public void after() {
        if(driver1 != null){
            driver1.quit();
        }
        if(driver2 != null){
            driver2.quit();
        }
        if(driver3 != null){
            driver3.quit();
        }
    }
}
