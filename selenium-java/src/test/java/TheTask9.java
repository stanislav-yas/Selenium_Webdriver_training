import org.junit.*;
import org.openqa.selenium.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.util.ArrayList;
import java.util.List;

public class TheTask9 extends TestBase{

    @Test
    public void subTask1(){
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        Assert.assertTrue("Login failed",checkLogin());
        wait.until(titleIs("Countries | My Store"));
        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        int countryCount = rows.size();
        ArrayList<String> countryHrefsToCheck = new ArrayList<String>();
        String prevCountry = "";
        for (WebElement row:rows) {
            WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));
            String country = countryLink.getText(); // get country name
            int compare = country.compareToIgnoreCase(prevCountry);
            Assert.assertTrue("Sorting failed on country: "+ country, compare >= 0);
            String numZonesStr = row.findElement(By.cssSelector("td:nth-child(6)")).getText(); // get number of zones
            if(Integer.parseInt(numZonesStr) > 0){
                countryHrefsToCheck.add(countryLink.getAttribute("href")); // add country href to check for zones
            }
            prevCountry = country;
        }
        System.out.println(countryCount + " countries sorted well in table");

        /* checking country zone sorting */
        for (String href:countryHrefsToCheck) {
            driver.navigate().to(href);
            wait.until(titleIs("Countries | My Store"));
            String country = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
            rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
            int zoneCount = rows.size();
            String prevZone = "";
            for (WebElement row:rows) {
                String zoneName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
                int compare = zoneName.compareToIgnoreCase(prevZone);
                Assert.assertTrue("Zone sorting failed on country: "+ country, compare >= 0);
            }
        }
    }

    @Test
    public void subTask2(){
        String href = "http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code=CA";
        driver.navigate().to(href);
        Assert.assertTrue("Login failed",checkLogin());
        wait.until(titleIs("Edit Country | My Store"));
        String country = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
        //List<WebElement> rows = driver.findElements(By.cssSelector("table#table-zones.dataTable tr"));
        List<WebElement> cells = driver.findElements(By.cssSelector("table#table-zones.dataTable tr:not(.header) td:nth-child(3)"));
        int zoneCount = cells.size()-1;
        String prevZoneName = "";
        for (int i = 0; i < zoneCount; i++) {
            String zoneName = cells.get(i).getText();
            int compare = zoneName.compareToIgnoreCase(prevZoneName);
            Assert.assertTrue("Zone sorting failed on country="+ country + ", zone=" + zoneName + ", href=" + href,
                    compare >= 0);
            prevZoneName = zoneName;
        }
        System.out.println(zoneCount + " zones sorted well in table country " + country);
    }

    private boolean checkLogin(){
        WebElement loginForm = null;
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
