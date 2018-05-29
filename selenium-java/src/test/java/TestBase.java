import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.machinepublishers.jbrowserdriver.*;

class TestBase {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        startChrome();
        //startFirefox();
        //startFirefoxDevEdition();
        //startIE();
        //startJBrowser();
        //startFirefoxHeadless();
        //startChromeHeadless();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        driver.quit();
    }

    WebElement findElementByCssSelector(WebElement parent, String cssSelector){
        WebElement element;
        try{
            if(parent == null) {
                element = driver.findElement(By.cssSelector(cssSelector));
            }else{
                element = parent.findElement(By.cssSelector(cssSelector));
            }
        }catch (NoSuchElementException ex){
            return null;
        }
        return  element;
    }

    private void startChrome(){
        driver = new ChromeDriver();
        /*
        ChromeOptions options = new ChromeOptions();
        //chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        */
    }

    private void startChromeHeadless(){
        driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
    }

    private void startFirefox(){
        driver = new FirefoxDriver();
        /*
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        options.setCapability(FirefoxDriver.MARIONETTE, false); // start Firefox before v.48
        driver = new FirefoxDriver(options);
        */
    }

    private void startFirefoxDevEdition(){
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
        driver = new FirefoxDriver(options);
    }

    private void startFirefoxHeadless(){
        driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
        //FirefoxBinary binary = new FirefoxBinary();
        //binary.addCommandLineOptions("--headless");
        //options.setBinary(binary);

    }

    private void startIE(){
        driver = new InternetExplorerDriver();
        /*
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(options);
        */
    }

    private void startJBrowser() {
        driver = new JBrowserDriver();
    }
}