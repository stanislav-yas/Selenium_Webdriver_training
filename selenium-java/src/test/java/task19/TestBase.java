package task19;

import org.junit.After;
import org.junit.Before;

public class TestBase {

    LiteStore liteStore;

    @Before
    public void setUp(){
        liteStore = new LiteStore();
    }

    @After
    public void tearDown(){
        liteStore.stop();
    }
}
