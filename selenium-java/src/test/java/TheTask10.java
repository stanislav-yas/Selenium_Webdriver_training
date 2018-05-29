import org.junit.*;
import org.openqa.selenium.*;
import java.awt.Color;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.*;

/*
* Задание 10. Проверить, что открывается правильная страница товара
* Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.
* Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:
* а) на главной странице и на странице товара совпадает текст названия товара
* б) на главной странице и на странице товара совпадают цены (обычная и акционная)
* в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
* г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
*    (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
* д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
*/

public class TheTask10 extends TestBase{

    @Test
    public void checkProductTest(){

        driver.navigate().to("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        List<WebElement>  productBoxes = driver.findElements(By.cssSelector("div#box-campaigns.box li.product"));
        Assert.assertTrue("No products found in block Campaigns",productBoxes.size() > 0 );
        WebElement productBox = productBoxes.get(0); // get first product LI element from main page
        String mainPageProductName = productBox.findElement(By.cssSelector("div.name")).getText();
        Product mainPageProduct = new Product(mainPageProductName, productBox); // create product object from main page
        String productUrl = productBox.findElement(By.cssSelector("a.link")).getAttribute("href");
        driver.navigate().to(productUrl);
        productBox = driver.findElement(By.cssSelector("div#box-product.box")); // get product from product page
        String productPageProductName = findElementByCssSelector(productBox,"h1.title").getText();
        Product productPageProduct = new Product(productPageProductName, productBox); // create product object from product page
        // checks from here
        Assert.assertEquals("Names of products are not equal", mainPageProduct.name, productPageProduct.name);
        Assert.assertEquals("Regular prices are not equal",
                mainPageProduct.regularPrice.value, productPageProduct.regularPrice.value);
        Assert.assertEquals("Campaign prices are not equal",
                mainPageProduct.campaignPrice.value, productPageProduct.campaignPrice.value);
        // checks for main page
        Assert.assertEquals("Regular price from main page is not crossed out",
                mainPageProduct.regularPrice.tagName, "s");
        Assert.assertTrue("Regular price color from main page is not gray",
                (mainPageProduct.regularPrice.color.getRed() == mainPageProduct.regularPrice.color.getGreen()
                && mainPageProduct.regularPrice.color.getRed() == mainPageProduct.regularPrice.color.getBlue()));
        Assert.assertEquals("Campaign price from main page is not bold",
                mainPageProduct.campaignPrice.tagName, "strong");
        Assert.assertTrue("Campaign price color from main page is not red",
                (mainPageProduct.campaignPrice.color.getGreen() == 0
                        && mainPageProduct.campaignPrice.color.getBlue() == 0));
        Assert.assertTrue("Campaign price is not bigger then regular on main page",
                mainPageProduct.campaignPrice.fontSize > mainPageProduct.regularPrice.fontSize);
        // checks for product page
        Assert.assertEquals("Regular price from product page is not crossed out",
                productPageProduct.regularPrice.tagName, "s");
        Assert.assertTrue("Regular price color from product page is not gray",
                (productPageProduct.regularPrice.color.getRed() == productPageProduct.regularPrice.color.getGreen()
                        && productPageProduct.regularPrice.color.getRed() == productPageProduct.regularPrice.color.getBlue()));
        Assert.assertEquals("Campaign price from product page is not bold",
                productPageProduct.campaignPrice.tagName, "strong");
        Assert.assertTrue("Campaign price color from product page is not red",
                (productPageProduct.campaignPrice.color.getGreen() == 0
                        && productPageProduct.campaignPrice.color.getBlue() == 0));
        Assert.assertTrue("Campaign price is not bigger then regular on product page",
                productPageProduct.campaignPrice.fontSize > productPageProduct.regularPrice.fontSize);
    }

    class Product{

        String name;
        Price regularPrice;
        Price campaignPrice;

        Product(String name, WebElement productBox){
            this.name = name;
            WebElement priceWrapper = findElementByCssSelector(productBox,"div.price-wrapper");
            regularPrice = new Price(priceWrapper, "regular-price");
            campaignPrice = new Price(priceWrapper, "campaign-price");
        }

    }

    class Price{

        String tagName = "";
        String value = "";
        Color color = null;
        double fontSize = 0;

        Price (WebElement priceWrapper, String priceClassName){

            WebElement priceTag = findElementByCssSelector(priceWrapper, "." + priceClassName);
            if(priceTag != null){
                tagName = priceTag.getTagName();
                value = priceTag.getText();
                color = parseColor(priceTag.getCssValue("color"));
                fontSize = parseFontSize(priceTag.getCssValue("font-size"));
            }
        }

        private Color parseColor(String colorStr){
            if(colorStr == null || colorStr.isEmpty()){
                return  null;
            }
            if(colorStr.contains("rgba")){
                int comma1ix = colorStr.indexOf(',');
                int red = Integer.parseInt(colorStr.substring(5,comma1ix));
                int comma2ix = colorStr.indexOf(',',comma1ix+1);
                int green = Integer.parseInt(colorStr.substring(comma1ix+1,comma2ix).trim());
                int comma3ix = colorStr.indexOf(',',comma2ix+1);
                int blue = Integer.parseInt(colorStr.substring(comma2ix+1,comma3ix).trim());
                int alpha = Integer.parseInt(colorStr.substring(comma3ix+1,colorStr.indexOf(')')).trim());
                return new Color(red,green,blue,alpha);
            }else if(colorStr.contains("rgb")){
                int comma1ix = colorStr.indexOf(',');
                int red = Integer.parseInt(colorStr.substring(4,comma1ix));
                int comma2ix = colorStr.indexOf(',',comma1ix+1);
                int green = Integer.parseInt(colorStr.substring(comma1ix+1,comma2ix).trim());
                int blue = Integer.parseInt(colorStr.substring(comma2ix+1,colorStr.indexOf(')')).trim());
                return new Color(red,green,blue,1);
            }
            return null;
        }

        private double parseFontSize(String fontSizeStr){
            double val;
            try{
                val = Double.parseDouble(fontSizeStr.substring(0,fontSizeStr.length()-2)); // cut "px"
            }catch (NumberFormatException ex){
                val = 0;
            }
            return val;
        }
    }

}
