import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Lesson5 extends TestBase{

    @Test
    public void examplesTest(){
        driver.navigate().to("https://output.jsbin.com/saqoca/2");
        WebElement el1 = driver.findElement(By.cssSelector("div#transparent"));
        boolean el1IsVisible = el1.isDisplayed();
        String el1Text = el1.getText();
        WebElement el2 = driver.findElement(By.cssSelector("div#white"));
        boolean el2IsVisible = el2.isDisplayed();
        String el2Text = el2.getText();
        String el2ColorStyle = el2.getCssValue("color");
        WebElement el3 = driver.findElement(By.cssSelector("div#outside"));
        boolean el3IsVisible = el3.isDisplayed();
        String el3Text = el3.getText();
        WebElement el4 = driver.findElement(By.cssSelector("div#behind"));
        boolean el4IsVisible = el4.isDisplayed();
        String el4Text = el4.getText();
        String el4Style = el4.getCssValue("background");
        WebElement el5 = driver.findElement(By.cssSelector("div#shifted"));
        boolean el5IsVisible = el5.isDisplayed();
        String el5Text = el5.getText();
        String el5TextContent = el5.getAttribute("textContent");
    }
}
