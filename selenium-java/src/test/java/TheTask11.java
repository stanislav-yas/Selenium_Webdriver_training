import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.*;

// Задание 11. Сделайте сценарий регистрации пользователя
//
//        Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).
//        Сценарий должен состоять из следующих частей:
//        1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями,
//          в том числе при предыдущих запусках того же самого сценария),
//        2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
//        3) повторный вход в только что созданную учётную запись,
//        4) и ещё раз выход.
//
//        В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.

public class TheTask11 extends TestBase{

    @Test
    public void createAccountTest(){

        String firstName = "Donald", lastName = "Trump", address = "725 5th Ave", city = "New York", postcode = "10022";
        String namePart = "d_trump", hostPart = "@gmail.com", phone = "+1-123-456-789", password = "password";
        String email;
        // 1 Step (Sign in)
        driver.navigate().to("http://localhost/litecart/en/create_account");
        wait.until(titleIs("Create Account | My Store"));
        findElementByCssSelector(null, "input[name=firstname]").sendKeys(firstName);
        findElementByCssSelector(null, "input[name=lastname]").sendKeys(lastName);
        findElementByCssSelector(null, "input[name=address1]").sendKeys(address);
        findElementByCssSelector(null, "input[name=postcode]").sendKeys(postcode);
        findElementByCssSelector(null, "input[name=city]").sendKeys(city);
        WebElement select = findElementByCssSelector(null,"select[name=country_code]");
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='US'; arguments[0].dispatchEvent(new Event('change'))", select);
        select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name=zone_code]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='NY'; arguments[0].dispatchEvent(new Event('change'))", select);
        findElementByCssSelector(null, "input[name=phone]").sendKeys(phone);
        if(findElementByCssSelector(null, "input[name=newsletter]").isSelected()){
            findElementByCssSelector(null, "input[name=newsletter]").click();
        }
        WebElement errorNotice;
        do{
            email = namePart + new Random().nextInt(99) + hostPart; // generate new email
            findElementByCssSelector(null, "input[name=email]").clear();
            findElementByCssSelector(null, "input[name=email]").sendKeys(email);
            findElementByCssSelector(null, "input[name=password]").sendKeys(password);
            findElementByCssSelector(null, "input[name=confirmed_password]").sendKeys(password);
            findElementByCssSelector(null, "button[name=create_account]").click();
            errorNotice = findElementByCssSelector(null, "div.notice.errors");
        }while(errorNotice != null && errorNotice.isDisplayed()); // do again if email account already exist
        wait.until(titleIs("Online Store | My Store"));
        // new customer account created

        if(findElementByCssSelector(null, "div#box-account") != null){
            // 2 Step (Logout)
            driver.findElement(By.linkText("Logout")).click();
        }
        wait.until(titleIs("Online Store | My Store"));
        if(findElementByCssSelector(null, "div#box-account-login") != null){
            // customer logged out
            // 3 Step (Login)
            findElementByCssSelector(null, "input[name=email]").sendKeys(email);
            findElementByCssSelector(null, "input[name=password]").sendKeys(password);
            findElementByCssSelector(null, "button[name=login]").click();
            errorNotice = findElementByCssSelector(null, "div.notice.errors");
            if(errorNotice != null && errorNotice.isDisplayed()){
                Assert.fail("Login failed");
            }
        }
        wait.until(titleIs("Online Store | My Store"));
        if(findElementByCssSelector(null, "div#box-account") != null){
            // new customer logged in
            // 4 Step (Logout)
            driver.findElement(By.linkText("Logout")).click();
        }
    }

}
