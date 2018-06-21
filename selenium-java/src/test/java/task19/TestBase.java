package task19;

import org.junit.After;
import org.junit.Before;

public class TestBase {

    LiteStore liteStore;

    @Before
    public void start(){
        liteStore = new LiteStore();
    }

    @After
    public void stop(){
        liteStore.stop();
    }
}
