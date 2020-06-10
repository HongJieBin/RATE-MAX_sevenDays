import com.memory.controller.MsgController;
import com.memory.controller.VO.ChatRoomMsgVO;
import com.memory.dao.MsgDAO;
import com.memory.dao.PersonDao;
import com.memory.dao.UserDAO;
import com.memory.pojo.Msg;
import com.memory.pojo.User;
import com.memory.service.ChatMsgService;
import com.memory.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appliciationContext.xml")
@WebAppConfiguration
public class TestMsg {
    @Autowired
    private MsgDAO msgDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MsgController msgController;
    @Autowired
    private ChatMsgService chatMsgService;
    @Autowired
    private UserService userService;

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
        //String json = msgController.getUnReadMsgList(100);
        //System.out.println(json);
        //List<ChatRoomMsgVO> chatRoomMsgVOS = chatMsgService.getAllChatMsgList(26,1);
        //System.out.println(chatRoomMsgVOS);
        chatMsgService.save(2,2,"123456");
        //List<Integer> x = new ArrayList<>();
        //x.add(6);
        //x.add(7);
        //x.add(8);
        //chatMsgService.updateChatMsgSigned(x);
        User user = userDAO.get(34);
        System.out.println(user);

        try {
            if(userService.userIsLocked("15060962397")) System.out.println(1);
            else System.out.println(2);
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
