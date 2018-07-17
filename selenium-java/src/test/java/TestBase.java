import org.junit.*;
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
    public void setUp() {
        startChrome();
        //startFirefox();
        //startIE();
        //startFirefoxDevEdition();
        //startJBrowser();
        //startFirefoxHeadless();
        //startChromeHeadless();
        //System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void tearDown() {
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

    boolean checkLogin(String username, String password){

        WebElement loginForm;
        try{
            loginForm = driver.findElement(By.cssSelector("form[name=login_form]"));
        }catch (NoSuchElementException ex){
            return true; // Login form not found - already logged in
        }
        try {
            loginForm.findElement(By.name("username")).sendKeys(username);
            loginForm.findElement(By.name("password")).sendKeys(password);
            loginForm.findElement(By.name("login")).click();
        }catch (NoSuchElementException ex){
            Assert.fail("Login failed");
        }
        try{
            driver.findElement(By.cssSelector("form[name=login_form]"));
            return false; // unsuccessful login
        }catch (NoSuchElementException ex){
            return true; // successful login
        }
    }

    private void startChrome(){
        driver = new ChromeDriver();
        /*
        ChromeOptions options = new ChromeOptions();
        //chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\po.LiteStore\\chrome.exe");
        options.addArguments("start-maximized");
        driver1 = new ChromeDriver(options);
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
        driver1 = new FirefoxDriver(options);
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
        driver1 = new InternetExplorerDriver(options);
        */
    }

    private void startJBrowser() {
        driver = new JBrowserDriver();
    }
}