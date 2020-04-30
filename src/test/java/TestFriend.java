import com.memory.controller.FriendController;
import com.memory.dao.FriendDAO;
import com.memory.pojo.Friend;
import com.memory.service.FriendService;
import com.memory.utils.JsonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appliciationContext.xml")
public class TestFriend {
    @Resource
    private FriendController friendController;
    @Resource
    private FriendDAO friendDAO;
    @Resource
    private FriendService friendService;

    @Test
    public  void testFriend(){
        Friend friend = new Friend();
        friend.setAddedId(8);
        friend.setUserId(6);
        friend.setLevel(1);
        //friendDAO.add(friend);
        //friendService.queryFriendsList(8);
        List<Friend> friendList = friendDAO.getByUserID(8);
        String json = friendController.getFriends(8);
        System.out.println(json);
    }

    @Test
    public void testExitUser(){
        if(friendService.isExitUser(100))
            System.out.println("ok");
        else System.out.println("xxx");

    }


}
