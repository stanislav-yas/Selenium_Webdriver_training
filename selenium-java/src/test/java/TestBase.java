import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.machinepublishers.jbrowserdriver.*;

public class TestBase {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        //startChrome();
        //startIE();
        //startJBrowser();
        //startFirefoxHeadless();
        //startFirefox();
        startChromeHeadless();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        driver.quit();
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
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

    private void startFirefoxHeadless(){
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        driver = new FirefoxDriver(firefoxOptions);
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