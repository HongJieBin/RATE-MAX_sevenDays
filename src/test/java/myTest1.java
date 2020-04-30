import com.memory.dao.*;
import com.memory.pojo.Memory;
import com.memory.pojo.Person;
import com.memory.pojo.Tag;
import com.memory.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appliciationContext.xml")
public class myTest1 {

    //@Resource(name = "personDao")
    //@Autowired
    //private PersonDao personDao;
    //@Resource(name = "userDAOImpl")
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MemoryDAO memoryDAO;
    @Autowired
    private TagDAO tagDAO;
    /*
    @Test
    public void testSave() {
        Person p = new Person();
        p.setUserName("Winbee");
        p.setSex("女");
        p.setPassword("123");
        System.out.println(p);
        personDao.save(p);

    }
    @Test
    public void testGetPerson(){
        Person p = personDao.getPersonByUserName("Winbee");
        System.out.println("testGetPerson:" + p);
    }*/
    /*@Test
    public void testUserDAO(){


    }*/
    @Test
    public void testMemoryDAO(){
        User u1 = new User();
        u1.setTelephone("999");
        u1.setNickname("hkb");
        u1.setPassword("77777");
        u1.setProfile("你瞅啥？");
        //u1.setUserId(45);

        //System.out.println("Add User:" + u1);
       // userDAO.add(u1);

        Memory m = new Memory();
        m.setMemoryTitle("xxx");
        m.setMemoryContent("!!!");
        m.setUser(u1);
        //memoryDAO.add(m);

        User user  = userDAO.get(6);
        System.out.println("User:" + user);
        userDAO.delete(user);
        //Memory memory = memoryDAO.get(6);
        //System.out.println("Memory:" +memory);
        //memoryDAO.delete(memory);
    }
   /* @Test
    public void testTagDAO(){
        User u1 = new User();
        u1.setTelephone("110");
        u1.setNickname("FuseHelium-3");
        u1.setPassword("12345");
        u1.setProfile("你瞅啥？");

        System.out.println("Add User:" + u1);
        User u2 = new User();
        u2.setTelephone("911");
        u2.setNickname("Supremacy");
        u2.setPassword("7777");
        u2.setProfile("瞅你咋地？");

        System.out.println("Add User:" + u2);

        Set<User> users = new HashSet<>();
        users.add(u1);


        Tag t1 = new Tag();
        t1.setTagName("失恋");

        Tag t2 = new Tag();
        t2.setTagName("自闭");
        Set<Tag> tags = new HashSet<>();

        tagDAO.add(t1);
        tagDAO.add(t2);
        tags.add(t1);
        tags.add(t2);
        u1.setTags(tags);
        userDAO.add(u1);
        userDAO.add(u2);

        User u3 = userDAO.get(1);
        Set<Tag> tagSet = new HashSet<>();
        tagSet = u3.getTags();
        Iterator<Tag> iterator = tagSet.iterator();
        while(iterator.hasNext()){
            Tag tmp = iterator.next();
            System.out.println(tmp);
        }

        //userDAO.delete(u3);



    }*/


}
