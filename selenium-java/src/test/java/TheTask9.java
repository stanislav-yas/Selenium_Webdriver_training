import org.junit.*;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.util.ArrayList;
import java.util.List;

/*
* Задание 9:
* Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.
*/

public class TheTask9 extends TestBase{

    /*
    * 1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
    * а) проверить, что страны расположены в алфавитном порядке
    * б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить,
    *    что зоны расположены в алфавитном порядке
    */

    @Test
    public void Test1(){

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        Assert.assertTrue("Login failed",checkLogin());
        wait.until(titleIs("Countries | My Store"));
        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        ArrayList<String> hrefs = new ArrayList<>();
        String prevCountry = "";
        for (WebElement row:rows) {
            WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));
            String country = countryLink.getText(); // get country name
            int compare = country.compareToIgnoreCase(prevCountry); // current and previous country names comparing
            Assert.assertTrue("Sorting failed on country: "+ country, compare >= 0); // sorting check
            String numZonesStr = row.findElement(By.cssSelector("td:nth-child(6)")).getText(); // get number of zones
            if(Integer.parseInt(numZonesStr) > 0){
                hrefs.add(countryLink.getAttribute("href")); // add country href to check for zones
            }
            prevCountry = country;
        }
        System.out.println(rows.size() + " rows sorted well in table Countries");

        /* checking country zone sorting */
        for (String href:hrefs) {
            driver.navigate().to(href);
            //Assert.assertTrue("Login failed",checkLogin());
            wait.until(titleIs("Edit Country | My Store"));
            String country = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
            String cellsSel = "table#table-zones.dataTable tr:not(.header) td:nth-child(3)"; // zone names cells cssSelector
            List<WebElement> cells = driver.findElements(By.cssSelector(cellsSel));
            int zoneCount = cells.size()-1;
            String prevZoneName = "";
            for (int i = 0; i < zoneCount; i++) {
                String zoneName = cells.get(i).getText();
                int compare = zoneName.compareToIgnoreCase(prevZoneName); // current and previous zone names comparing
                Assert.assertTrue("Zone sorting failed on country="+ country + ", zone=" + zoneName + ", href=" + href,
                        compare >= 0); // sorting check
                prevZoneName = zoneName;
            }
            System.out.println(zoneCount + " zones sorted well for country=" + country);
        }
    }

    /*
    * 2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
    *    зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
    */

    @Test
    public void Test2(){

        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        Assert.assertTrue("Login failed",checkLogin());
        wait.until(titleIs("Geo Zones | My Store"));
        String selector = "tr.row td:nth-child(3) a"; // Geo zones tags A selector
        List<WebElement> cells = driver.findElements(By.cssSelector(selector));
        ArrayList<String> hrefs = new ArrayList<>(); // Geo zones pages link collection
        for(WebElement cell:cells){
            hrefs.add(cell.getAttribute("href"));
        }
        for(String href:hrefs){
            driver.navigate().to(href); // go to next Geo zone page
            wait.until(titleIs("Edit Geo Zone | My Store"));
            String geoZoneName = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
            selector = "table#table-zones.dataTable tr td:nth-child(3) option[selected]"; // zones selector
            cells = driver.findElements(By.cssSelector(selector));
            String prevZoneName = "";
            for(WebElement cell:cells){
                String zoneName = cell.getText();
                int compare = zoneName.compareToIgnoreCase(prevZoneName); // current and previous zone names comparing
                Assert.assertTrue("Zone sorting failed on Geo zone="+ geoZoneName + ", zone=" + zoneName + ", href=" + href,
                        compare >= 0); // sorting check
                prevZoneName = zoneName;
            }
            System.out.println(cells.size() + " zones sorted well for Geo zone=" + geoZoneName);
        }
    }

    private boolean checkLogin(){

        WebElement loginForm;
        try{
            loginForm = driver.findElement(By.cssSelector("form[name=login_form]"));
        }catch (NoSuchElementException ex){
            return true; // already logged in
        }
        try {
            loginForm.findElement(By.name("username")).sendKeys("admin");
            loginForm.findElement(By.name("password")).sendKeys("admin");
            loginForm.findElement(By.name("login")).click();
        }catch (NoSuchElementException ex){
            return false; // unsuccessful login
        }
        try{
            driver.findElement(By.cssSelector("form[name=login_form]"));
            return false; // unsuccessful login
        }catch (NoSuchElementException ex){
            return true; // successful login
        }
    }
}
