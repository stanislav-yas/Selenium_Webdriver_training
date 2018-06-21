import org.junit.After;
import org.junit.Before;

public class BaseTest {

    LiteStore liteStore;

    @Before
    public void start(){
        liteStore = new LiteStore();
    }

    @After
    public void stop(){
        liteStore.quit();
    }
}
