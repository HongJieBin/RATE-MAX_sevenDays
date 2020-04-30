import com.memory.dao.BlacklistDAO;
import com.memory.dao.FriendDAO;
import com.memory.dao.MsgDAO;
import com.memory.dao.UserDAO;
import com.memory.pojo.Blacklist;
import com.memory.pojo.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appliciationContext.xml")
public class testFri_Black {
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private BlacklistDAO blacklistDAO;

    @Test
    public void testFri_Black() {
        Friend friend = new Friend();
        friend.setUserId(6);
        friend.setAddedId(7);
        //friend.setLevel(5);
        //friend.setRemark("xxx");
        friendDAO.add(friend);
        //friendDAO.delete(friend);
      /*Blacklist blacklist = new Blacklist();
        blacklist.setAddedId(6);
        blacklist.setUserId(7);
        blacklistDAO.add(blacklist);*/
    }
}
