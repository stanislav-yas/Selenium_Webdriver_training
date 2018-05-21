import org.junit.Test;
import org.openqa.selenium.*;

public class TryCookiesTest extends TestBase{

    @Test
    public void tryCookiesTest(){
        driver.navigate().to("https://yandex.ru/internet/");
        driver.manage().addCookie(new Cookie("test", "test"));
        java.util.Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie: cookies) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }
        driver.manage().deleteCookieNamed("test");
        driver.manage().deleteAllCookies();
    }
}
