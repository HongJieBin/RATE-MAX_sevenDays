import com.memory.controller.MsgController;
import com.memory.dao.MsgDAO;
import com.memory.dao.PersonDao;
import com.memory.dao.UserDAO;
import com.memory.pojo.Msg;
import com.memory.service.ChatMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appliciationContext.xml")
public class TestMsg {
    @Autowired
    private MsgDAO msgDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MsgController msgController;

    @Test
    public void testMsgDAO() {
        Timestamp time = new Timestamp(new Date().getTime());
        Msg msg = new Msg();
        msg.setMsgContent("xxx");
        msg.setMsgDatetime(time);
        msg.setSendUser(userDAO.get(7));
        msg.setReceiveUser(userDAO.get(6));
        msgDAO.add(msg);
    }

    @Test
    public void testMsg(){
        String json = msgController.getUnReadMsgList(100);
        System.out.println(json);
    }


}
