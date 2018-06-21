package task19;

import org.junit.After;
import org.junit.Before;

public class TestBase {

    Application app;

    @Before
    public void start(){
        app = new Application();
    }

    @After
    public void stop(){
        app.quit();
    }
}
