package task19.pages;

public class Page extends PageBlock{

    protected String pageUrl;

    public void open(){
        driver.navigate().to(pageUrl);
    }
}
