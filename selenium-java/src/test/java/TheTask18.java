// Задание 18. Перенаправьте трафик в прокси-сервер
//
//    Установите какой-нибудь прокси-сервер, который умеет протоколировать запросы и ответы.
//    На выбор прокси-сервера для разных платформ:
//    http://www.telerik.com/fiddler (Windows)
//    https://www.charlesproxy.com/ (Windows, Linux, MacOS, платный, но есть пробная версия)
//    https://mitmproxy.org/ (Linux, MacOS)
//    https://www.owasp.org/index.php/OWASP_Zed_Attack_Proxy_Project (Windows, LInux, MacOS)
//
//    Инициализируйте драйвер так, чтобы запросы из браузера отправлялись через этот прокси-сервер, убедитесь, что они там видны.

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class TheTask18{

    WebDriver driver;
    BrowserMobProxy proxy;

    @Before
    public void start(){
        proxy = new BrowserMobProxyServer(); //https://github.com/lightbody/browsermob-proxy
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = new ChromeDriver(chromeOptions);
        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    }

    @After
    public void stop(){
        if(driver != null){
            driver.quit();
        }
        proxy.stop();
    }

    @Test
    public void useProxy(){
        proxy.newHar("selenium2");
        driver.navigate().to("https://selenium2.ru");
        // get the HAR data
        Har har = proxy.getHar();
        har.getLog().getEntries().forEach(harEntry -> System.out.println(harEntry.getResponse().getStatus() + ":" +
            harEntry.getRequest().getUrl()));
    }
}
