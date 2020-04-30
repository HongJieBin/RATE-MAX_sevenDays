import com.memory.netty.ChatServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appliciationContext.xml")
public class testServer {


    @Test
    public  void testServer() throws Exception {
        ChatServer chatServer = new ChatServer(7888);
        chatServer.run();
    }

}
