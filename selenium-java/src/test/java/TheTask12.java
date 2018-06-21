// Задание 12. Сделайте сценарий добавления товара
//
//        Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
//        Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New po.Product",
//         заполнить поля с информацией о товаре и сохранить.
//        Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campaigns) на вкладке Prices можно не добавлять.
//        Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу.
//        Картинку с изображением товара нужно уложить в репозиторий вместе с кодом.
//        При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет.
//        Надо средствами языка программирования преобразовать относительный путь в абсолютный.
//        После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.List;
import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TheTask12 extends TestBase{

    @Test
    public void addNewProduct(){
        String productName = "Red Star Duck";
        driver.navigate().to("http://localhost/litecart/admin");
        Assert.assertTrue("Login failed",checkLogin("admin","admin"));
        wait.until(titleIs("My Store"));
        findElementByCssSelector(null, "ul#box-apps-menu li:nth-child(2)").click(); // Catalog menu
        wait.until(titleIs("Catalog | My Store"));
        findElementByCssSelector(null, "td#content a.button:nth-child(2)").click(); // Add New po.Product button
        wait.until(titleIs("Add New po.Product | My Store"));
        findElementByCssSelector(null,"#tab-general input[name='status']").click(); // set Status enabled
        findElementByCssSelector(null,"#tab-general input[name='name[en]']")
                .sendKeys(productName); // enter Name
        findElementByCssSelector(null,"#tab-general input[name='code']")
                .sendKeys("rd999"); // enter Code
        findElementByCssSelector(null,"#tab-general input[name='categories[]'][data-name='Root']")
                .click(); // uncheck Root category
        findElementByCssSelector(null,"#tab-general input[name='categories[]'][data-name='Rubber Ducks']")
                .click(); // check Rubber Ducks category
        WebElement element = findElementByCssSelector(null,"#tab-general input[name='quantity']");
        element.clear();
        element.sendKeys("25"); // input quantity
        findElementByCssSelector(null,"#tab-general input[name='new_images[]']")
                .sendKeys(new File("src\\test\\resources\\red-star-duck.png").getAbsolutePath()); // enter product image file
        findElementByCssSelector(null,"div.tabs ul.index a[href='#tab-information']")
                .click(); // click Information tab
        findElementByCssSelector(null,"#tab-information select[name='manufacturer_id'] option[value='1']")
                .click(); // set Manufacturer
        findElementByCssSelector(null,"#tab-information input[name='short_description[en]']")
                .sendKeys("This is a short description of the Red Star Duck");
        findElementByCssSelector(null,"#tab-information .trumbowyg-editor")
                .sendKeys("This is a full description of the Red Star Duck\n\rWhat a wonderful thing it is!");
        findElementByCssSelector(null,"div.tabs ul.index a[href='#tab-prices']")
                .click(); // click Prices tab
        element = findElementByCssSelector(null,"#tab-prices input[name='purchase_price']");
        element.clear();
        element.sendKeys("35"); // enter Purchase price
        findElementByCssSelector(null,"#tab-prices select[name='purchase_price_currency_code'] option[value='USD']")
                .click(); // select USD
        findElementByCssSelector(null,"#tab-prices input[name='prices[USD]']")
                .sendKeys("50"); // enter Purchase price
        findElementByCssSelector(null,".button-set > button[name=save]")
                .click(); // save product
        Assert.assertTrue("New Product: " + productName + " addition failed",
                findElementByCssSelector(null,"div.notice.success").isDisplayed());
        //wait.until(titleIs("Catalog | My Store"));
        // newly added product checking
        List<WebElement> anchors = driver.findElements(By.cssSelector("td#content tr.row > td:nth-child(3) > a"));
        boolean found = false;
        for(WebElement anchor:anchors){
            found = anchor.getText().equals(productName);
            if(found) break; // newly added product found
        }
        Assert.assertTrue("Newly added product: " + productName + " not found in Catalog",found);
    }

}
